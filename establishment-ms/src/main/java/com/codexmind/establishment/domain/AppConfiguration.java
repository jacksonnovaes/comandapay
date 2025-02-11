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

    @Column(name = "primary_color")
    private String primary;

    private String contrastPrimaryColor;

    @Column(name = "secondary_color")
    private String secondary;

    private String contrastSecondaryColor;

    private Boolean firstLogin = Boolean.TRUE;

    private String mode;


}
