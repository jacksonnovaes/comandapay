    package com.codexmind.establishment.usecases.appConfig;

import com.codexmind.establishment.domain.AppConfiguration;
import com.codexmind.establishment.dto.AppConfigDTO;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.AppConfigurationRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveAppConfig {

    private final AppConfigurationRepository appConfigurationRepository;

    private final AppConfig appConfig;

    public SaveAppConfig(AppConfigurationRepository appConfigurationRepository, AppConfig appConfig) {
        this.appConfigurationRepository = appConfigurationRepository;
        this.appConfig = appConfig;
    }

    public AppConfiguration execute(AppConfigDTO appConfigDTO, Integer establishimentId){
        var appConfigFinded = appConfig.execute(establishimentId).orElseThrow(
                () -> new EntityNotFoundException("configuracao nao encontrada!")
        );
        AppConfiguration appConfig = new AppConfiguration();
        appConfig.setId(appConfigFinded.getId());
        appConfig.setEstablishmentId(establishimentId);
        appConfig.setPrimaryColor(appConfigDTO.primaryColor());
        appConfig.setSecondaryColor(appConfigDTO.secondaryColor());
        appConfig.setContrastPrimaryColor(appConfigDTO.contrastPrimaryColor());
        appConfig.setContrastSecondaryColor(appConfigDTO.contrastSecondaryColor());
        appConfig.setMode(appConfigDTO.mode());
        appConfig.setFirstLogin(Boolean.FALSE);
        return appConfigurationRepository.save(appConfig);
    }
}
