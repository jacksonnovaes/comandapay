package com.codexmind.establishment.domain.enums;

import java.util.Arrays;

public enum Status {
    
    ACTIVE("ATIVO"),
    INACTIVE("INATIVO");

    private final String value;
    Status(String value) {
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
