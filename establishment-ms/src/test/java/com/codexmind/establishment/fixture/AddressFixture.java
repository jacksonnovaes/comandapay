package com.codexmind.establishment.fixture;

import com.codexmind.establishment.domain.Address;

public class AddressFixture {

    public static Address getAddress(){
        return Address.builder()
                .postalCode("05077010")
                .name("av Ernesto Igel")
                .complemento("apto 45")
                .number("307")
                .city("sao paulo")
                .uf("SP")
                .build();
    }

}
