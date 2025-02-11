package com.codexmind.establishment.usecases.establishment;


import com.codexmind.establishment.domain.Address;
import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.dto.EstablishmentDTO;
import com.codexmind.establishment.repository.EstablishmentRepository;
import com.codexmind.establishment.service.ServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveEstablishment {

    private final ServiceClient serviceClient;

    private final EstablishmentRepository repository;

    public Establishment execute(EstablishmentDTO establishmentDTO) {
        var addressDTO = serviceClient.buscaEnderecoPorCep(establishmentDTO.postalCode());


        var address = Address.builder()
                .name(addressDTO.logradouro())
                .number(establishmentDTO.number())
                .complemento(establishmentDTO.complement())
                .postalCode(addressDTO.cep())
                .bairro(addressDTO.bairro())
                .city(addressDTO.localidade())
                .uf(addressDTO.uf())
                .build();

        var establishment = Establishment.builder()
                .name(establishmentDTO.name())
                .cnpj(establishmentDTO.cnpj())
                .menus(null)
                .address(address)
                .status(Status.ACTIVE)
                .build();

        return repository.save(establishment);
    }
}
