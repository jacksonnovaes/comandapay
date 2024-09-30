package com.codexmind.establishment.usecases.appConfig;

import com.codexmind.establishment.domain.AppConfiguration;
import com.codexmind.establishment.repository.AppConfigurationRepository;
import com.codexmind.establishment.repository.EstablishmentRepository;
import com.codexmind.establishment.usecases.establishment.GetEstablishmentByEmployeLogin;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppConfig {

    private final AppConfigurationRepository appConfigurationRepository;

    private final GetEstablishmentByEmployeLogin establishmentByEmployeLogin;

    public AppConfig(AppConfigurationRepository appConfigurationRepository, GetEstablishmentByEmployeLogin establishmentByEmployeLogin) {
        this.appConfigurationRepository = appConfigurationRepository;
        this.establishmentByEmployeLogin = establishmentByEmployeLogin;
    }

    public Optional<AppConfiguration> execute(Integer establishmentId){


        return appConfigurationRepository.findByEstablishmentId(establishmentId);
    }
}
