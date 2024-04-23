package com.codexmind.establishment;

import com.codexmind.establishment.domain.*;
import com.codexmind.establishment.domain.enums.Profile;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.repository.*;
import com.codexmind.establishment.service.ServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.Set;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
public class EstablishmentMsApplication implements CommandLineRunner {

	private final EstablishmentRepository establishmentRepository;

	private final MenuRepository menuRepository;

	private final ProductRepository productRepository;

	private final EmployeeRepository employeeRepository;

	private final ServiceClient serviceClient;

	private final PasswordEncoder encoder;

	private final UserRepository userRepository;
	private final CustomerRepository customerRepository;

	private final Credentials credentials;

	public static void main(String[] args) {
		SpringApplication.run(EstablishmentMsApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {}
}
