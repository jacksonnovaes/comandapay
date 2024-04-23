package com.codexmind.establishment.fixture;

import com.codexmind.establishment.domain.Establishment;

public class EstablishmentFixture {

    public static Establishment savedEstablishment(){
        return Establishment.builder()
                .name("baar II")
                .cnpj("1198927492320000102")
                .address(AddressFixture.getAddress())
                .build();

    }
}

