package com.codexmind.establishment.domain.enums;

import java.util.Arrays;

public enum StatusComanda {
    CLOSED("CLOSED"),
    OPENED("OPENED");

    private final String value;


    StatusComanda(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Status fromValue(String statusValue) {

        return Arrays.stream(Status.values())
                .filter(situation -> situation.value.equals(statusValue))
                .findFirst().orElse(null);
    }
}
