package com.codexmind.establishment.converters;


import com.codexmind.establishment.domain.Cargo;
import com.codexmind.establishment.dto.CargoDTO;

public class CargoDtoConverter {

    public static CargoDTO toDTO(Cargo cargo) {
        return new CargoDTO(
                cargo.getName()
        );
    }

}
