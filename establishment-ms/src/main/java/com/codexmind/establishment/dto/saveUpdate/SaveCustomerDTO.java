package com.codexmind.establishment.dto.saveUpdate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record SaveCustomerDTO(

        Long id,

        @NotBlank(message = "o nome é obrigatório!")
        String name,

        @NotBlank
        String lastName,

        @NotBlank
        @CPF
        String cpf,


        @NotBlank
        String phone,


        @NotBlank
        String celPhone,


        @NotBlank
        String postalCode,

        String number,

        String placeName,

        String complemento,
        String bairro,
        String localidade,
        String uf,
        String city,

        @NotBlank
        @Email
        String login,

        @NotBlank
        @Length(min = 7)
        String pass,

        @NotNull
        @Past
        LocalDate birthDate
) {
}


