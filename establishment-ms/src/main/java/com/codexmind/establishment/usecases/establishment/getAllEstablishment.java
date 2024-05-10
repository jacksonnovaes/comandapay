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
public class getAllEstablishment {

    private final EstablishmentRepository establishmentRepository;

    public List<Establishment> execute(Integer idCustomer) {
        List<Establishment> establishments = establishmentRepository.findAllEstablishment(Status.ACTIVE);

        if (establishments.isEmpty()) {
            throw new EntityNotFoundException("Nenhum estabelecimento favorito encontrado para o cliente com ID: " + idCustomer);
        }
            return establishments;

    }
}
