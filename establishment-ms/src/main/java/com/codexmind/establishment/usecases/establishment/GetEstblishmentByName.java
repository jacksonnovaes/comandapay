package com.codexmind.establishment.usecases.establishment;


import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetEstblishmentByName {

    private final EstablishmentRepository establishmentRepository;

    public List<Establishment> execute(String name) {
        List<Establishment> establishments;
        if (name != null && !name.isEmpty()) {
            establishments = establishmentRepository.findEstablishmentByName(name, Status.ACTIVE);
            if (establishments.isEmpty()) {
                throw new EntityNotFoundException("Nenhum estabelecimento encontrado com o nome: " + name);
            }
        } else {
            establishments = establishmentRepository.findAllEstablishment(Status.ACTIVE);
        }
        return establishments;

    }
}
