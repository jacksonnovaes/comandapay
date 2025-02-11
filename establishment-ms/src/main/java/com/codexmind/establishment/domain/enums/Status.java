package com.codexmind.establishment.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Status {

    INACTIVE("INATIVO"),
    ACTIVE("ATIVO");

    public final String value;

    Status(String value) {
        this.value = value;
    }

    public static Status fromValue(String statusValue) {

        return Arrays.stream(Status.values())
                .filter(situation -> situation.value.equals(statusValue))
                .findFirst().orElse(null);
    }
}
