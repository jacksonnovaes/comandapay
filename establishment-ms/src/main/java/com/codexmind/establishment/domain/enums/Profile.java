package com.codexmind.establishment.domain.enums;

import lombok.Getter;

@Getter
public enum Profile {
    ESTABLISHMENT_ADMIN(1, "ESTABLISHMENT_ADMIN"),
    CLIENT(2,"CLIENT"),
    EMPLOYEE_ESTABLISHMENT(3,"EMPLOYEE_ESTABLISHMENT");

    private final int cod;
    private final String description;
    Profile(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }


    public static Profile toEnum(Integer cod) {
        if (cod==null) {
            return null;
        }
        for(Profile x: Profile.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("tipo informado inválido");
    }


}

