package com.codexmind.establishment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_MATCHERS = {
            "/login/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/h2-console/**",
            "/api/v1/address/**",
            "/api/v1/product/**",
            "/api/v1/product/menu/**",
            "/api/v1/payment/**",
            "/api/v1/signup/**",
            "api/v1/customer/signup/save"

    };

    private static final String[] ADMIN_ESTABLISHMENT_MATCHERS = {
            "/api/v1/customer/save/**",
            "/api/v1/employee/menu/list/**",
            "/api/v1/employee/user/**",
            "/api/v1/pdv/open/**",
            "/api/v1/pdv/close/**",
            "/api/v1/establishment/admin/**",
            "/api/v1/pdv/order/**",
            "/api/v1/product/pdv/menu/**"
    };


    private static final String[] EMPLOYEE_ESTABLISHMENT_MATCHERS = {
            "/api/v1/customer/save/**",
            "/api/v1/payment/**",
            "/api/v1/employee/user/**",
            "/api/v1/pdv/open/**",
            "/api/v1/pdv/close/**",
            "/api/v1/establishment/admin/**",
            "/api/v1/pdv/savaAll/**",
            "/api/v1/pdv/order/**",
            "/api/v1/product/pdv/menu/**",
            "/api/v1/product/pdv/edit/**"


    };
    private static final String[] CLIENT_MATCHERS = {
            "/api/v1/order/**",
            "/api/v1/order/open/**",
            "/api/v1/payment/**",
            "/api/v1/establishment/**",
            "/api/v1/establishment/favorites/add/**",
            "/api/v1/establishment/favorites/**",
            "/api/v1/menu/**",
            "/api/v1/order/countOrders/**",
            "/api/v1/itemOrder/edit/**"

    };


    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
               .cors(c-> c.configurationSource( apiConfigurationSource()))
               .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS).permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS).permitAll()
                        .requestMatchers(HttpMethod.DELETE, PUBLIC_MATCHERS).permitAll()
                        .requestMatchers(HttpMethod.PUT, PUBLIC_MATCHERS).permitAll()
                        .requestMatchers(HttpMethod.POST, ADMIN_ESTABLISHMENT_MATCHERS).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, ADMIN_ESTABLISHMENT_MATCHERS).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, ADMIN_ESTABLISHMENT_MATCHERS).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, ADMIN_ESTABLISHMENT_MATCHERS).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, EMPLOYEE_ESTABLISHMENT_MATCHERS).hasAnyRole("EMPLOYEE","ADMIN")
                        .requestMatchers(HttpMethod.GET, EMPLOYEE_ESTABLISHMENT_MATCHERS).hasAnyRole("EMPLOYEE","ADMIN")
                        .requestMatchers(HttpMethod.PUT, EMPLOYEE_ESTABLISHMENT_MATCHERS).hasAnyRole("EMPLOYEE","ADMIN")
                        .requestMatchers(HttpMethod.DELETE, EMPLOYEE_ESTABLISHMENT_MATCHERS).hasAnyRole("EMPLOYEE","ADMIN")
                        .requestMatchers(HttpMethod.POST, CLIENT_MATCHERS).hasAnyRole("EMPLOYEE","ADMIN","CLIENT")
                        .requestMatchers(HttpMethod.GET, CLIENT_MATCHERS).hasAnyRole("EMPLOYEE","ADMIN","CLIENT")
                        .requestMatchers(HttpMethod.PUT, CLIENT_MATCHERS).hasAnyRole("EMPLOYEE","ADMIN","CLIENT")
                        .requestMatchers(HttpMethod.DELETE, CLIENT_MATCHERS).hasAnyRole("EMPLOYEE","ADMIN","CLIENT")
                        .anyRequest().authenticated()
                ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(header -> header.frameOptions(FrameOptionsConfig::sameOrigin))
                        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
         return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource apiConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "http://192.168.0.129:8080",
                "http://192.168.0.213:8080",
                "http://192.168.0.129:8081",
                "http://localhost:8080",
                "http://localhost:3000",
                "http://172.20.10.3:8081",//
                "http://172.20.10.3:8081"

        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowCredentials(false);
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
