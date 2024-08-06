package com.codexmind.establishment.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum StatusComanda {
    CLOSED("CLOSED"),
    OPENED("OPENED");

    private final String value;

    StatusComanda(String value) {
        this.value = value;
    }

    public static StatusComanda fromValue(String statusValue) {

        return Arrays.stream(StatusComanda.values())
                .filter(situation -> situation.value.equals(statusValue))
                .findFirst().orElse(null);
    }
}
