package com.codexmind.establishment.controller.pdv;


import com.codexmind.establishment.domain.AppConfiguration;
import com.codexmind.establishment.dto.AppConfigDTO;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.usecases.appConfig.AppConfig;
import com.codexmind.establishment.usecases.appConfig.SaveAppConfig;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"api/v1/pdv/config"})
public class AppConfigController {


    private final AppConfig appConfig;

    private final SaveAppConfig saveAppConfig;

    public AppConfigController(AppConfig appConfig, SaveAppConfig saveAppConfig) {
        this.appConfig = appConfig;
        this.saveAppConfig = saveAppConfig;
    }

    @GetMapping("/{establishmentId}")
    public ResponseEntity<AppConfigDTO> config(@PathVariable Integer establishmentId) {
        var configFinded = appConfig.execute(establishmentId).orElseThrow(
                () -> new EntityNotFoundException("configuracao nao encontrada!")
        );
        var newConfigDTO = new AppConfigDTO(
                configFinded.getId(),
                establishmentId,
                configFinded.getPrimary(),
                configFinded.getSecondary(),
                configFinded.getMode(),
                configFinded.getContrastPrimaryColor(),
                configFinded.getContrastSecondaryColor(),
                configFinded.getFirstLogin()
        );
        return ResponseEntity.ok(newConfigDTO);
    }

    @PutMapping("/{establishmentId}")
    public ResponseEntity<AppConfigDTO> updateConfig(@PathVariable Integer establishmentId,
                                                     @RequestBody AppConfigDTO appConfigDTO
    ) {

        var appCongiSaved = saveAppConfig.execute(appConfigDTO, establishmentId);
        var newConfigDTO = new AppConfigDTO(
                appCongiSaved.getId(),
                establishmentId,
                appCongiSaved.getPrimary(),
                appCongiSaved.getSecondary(),
                appCongiSaved.getContrastPrimaryColor(),
                appCongiSaved.getContrastSecondaryColor(),
                appCongiSaved.getMode(),
                appCongiSaved.getFirstLogin()
        );
        return ResponseEntity.ok(newConfigDTO);
    }
}
