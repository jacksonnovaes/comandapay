package com.codexmind.establishment.dto.saveUpdate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record SaveEmployeeDTO(

        @NotBlank(message = "o nome é obrigatório!")
        String name,

        @NotBlank String lastName,

        @NotBlank String cpf,

        @NotBlank String phone,


        @NotBlank String celPhone,


        @NotBlank String postalCode,

        String number,

        String placeName,

        String complemento,
        String bairro,
        String uf,
        String city,
        @Email(message = "o login deve ser um email válido!")
        String login,
        @NotEmpty
        @Length(min = 7, message = "a senha deve ter no mínimo 7 caracteres")
        String pass,

        @NotBlank
        String cargo
) {

}
