package com.codexmind.establishment.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "tb_app_config")
public class AppConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer establishmentId;

    private String primaryColor;

    private String contrastPrimaryColor;

    private String secondaryColor;

    private String contrastSecondaryColor;

    private Boolean firstLogin = Boolean.TRUE;

    private String mode;


}
