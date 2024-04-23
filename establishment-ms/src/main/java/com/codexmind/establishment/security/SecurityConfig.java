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

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final String[] PUBLIC_MATCHERS = {
            "/login/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/h2-console/**",
            "/api/v1/address/**",
            "/api/v1/admin/**",
            "/api/v1/establishment/**",
            "/api/v1/product/**",
            "/api/v1/payment/**",
            "/api/v1/signup/**"

    };

    private static final String[] ADMIN_ESTABLISHMENT_MATCHERS = {
            "/api/v1/customer/save/**",
            "/api/v1/order/**"
    };


    private static final String[] EMPLOYEE_ESTABLISHMENT_MATCHERS = {
            "/api/v1/customer/save/**",
            "/api/v1/order/**",
            "/api/v1/order/open/**",
            "/api/v1/payment/**",
    };
    private static final String[] CLIENT_MATCHERS = {
        "/api/v1/order/**",
        "/api/v1/order/open/**",
            "/api/v1/payment/**",

    };


    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.csrf(AbstractHttpConfigurer::disable)
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
                        .requestMatchers(HttpMethod.POST, EMPLOYEE_ESTABLISHMENT_MATCHERS).hasAnyRole("EMPLOYEE_ESTABLISHMENT","ADMIN")
                        .requestMatchers(HttpMethod.GET, EMPLOYEE_ESTABLISHMENT_MATCHERS).hasAnyRole("EMPLOYEE_ESTABLISHMENT","ADMIN")
                        .requestMatchers(HttpMethod.PUT, EMPLOYEE_ESTABLISHMENT_MATCHERS).hasAnyRole("EMPLOYEE_ESTABLISHMENT","ADMIN")
                        .requestMatchers(HttpMethod.DELETE, EMPLOYEE_ESTABLISHMENT_MATCHERS).hasAnyRole("EMPLOYEE_ESTABLISHMENT","ADMIN")
                        .requestMatchers(HttpMethod.POST, CLIENT_MATCHERS).hasAnyRole("EMPLOYEE_ESTABLISHMENT","ADMIN","CLIENT")
                        .requestMatchers(HttpMethod.GET, CLIENT_MATCHERS).hasAnyRole("EMPLOYEE_ESTABLISHMENT","ADMIN","CLIENT")
                        .requestMatchers(HttpMethod.PUT, CLIENT_MATCHERS).hasAnyRole("EMPLOYEE_ESTABLISHMENT","ADMIN","CLIENT")
                        .requestMatchers(HttpMethod.DELETE, CLIENT_MATCHERS).hasAnyRole("EMPLOYEE_ESTABLISHMENT","ADMIN","CLIENT")
                        .anyRequest().authenticated()
                ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(header -> header.frameOptions(FrameOptionsConfig::disable)).cors(cors->cors.disable())
                        .build();
        
        /* 
        csrf(csrf -> csrf.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                .requestMatchers(
                        HttpMethod.POST, PUBLIC_MATCHERS)
                .permitAll()
                .requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS)
                .permitAll()
                .requestMatchers(HttpMethod.POST, ADMIN_ESTABLISHMENT_MATCHERS).hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, ADMIN_ESTABLISHMENT_MATCHERS).hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, ADMIN_ESTABLISHMENT_MATCHERS).hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, ADMIN_ESTABLISHMENT_MATCHERS).hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, EMPLOYEE_ESTABLISHMENT_MATCHERS).hasRole("EMPLOYEE_ESTABLISHMENT")
                .requestMatchers(HttpMethod.GET, EMPLOYEE_ESTABLISHMENT_MATCHERS).hasRole("EMPLOYEE_ESTABLISHMENT")
                .requestMatchers(HttpMethod.PUT, EMPLOYEE_ESTABLISHMENT_MATCHERS).hasRole("EMPLOYEE_ESTABLISHMENT")
                .requestMatchers(HttpMethod.DELETE, EMPLOYEE_ESTABLISHMENT_MATCHERS).hasRole("EMPLOYEE_ESTABLISHMENT")
                .requestMatchers(HttpMethod.POST, CLIENT_MATCHERS).hasAnyRole("ESTABLISHMENT_ADMIN", "ADMIN", "CLIENT")
                .requestMatchers(HttpMethod.GET, CLIENT_MATCHERS).hasAnyRole("ESTABLISHMENT_ADMIN", "ADMIN", "CLIENT")
                .requestMatchers(HttpMethod.PUT, CLIENT_MATCHERS).hasAnyRole("ESTABLISHMENT_ADMIN", "ADMIN", "CLIENT")
                .requestMatchers(HttpMethod.DELETE, CLIENT_MATCHERS).hasAnyRole("ESTABLISHMENT_ADMIN", "ADMIN", "CLIENT")

                .anyRequest().authenticated()).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .headers().frameOptions().disable().and().cors().and()
                .build();*/
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
         return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
