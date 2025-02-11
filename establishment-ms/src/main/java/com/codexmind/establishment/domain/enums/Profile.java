package com.codexmind.establishment.domain.enums;

import lombok.Getter;

@Getter
public enum Profile {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT"),
    EMPLOYEE(3, "ROLE_EMPLOYEE");

    private final int cod;
    private final String description;

    Profile(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }


    public static Profile toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Profile x : Profile.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("tipo informado inválido");
    }


}

