package com.codexmind.establishment;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
public class EstablishmentMsApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(EstablishmentMsApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
    }
}
