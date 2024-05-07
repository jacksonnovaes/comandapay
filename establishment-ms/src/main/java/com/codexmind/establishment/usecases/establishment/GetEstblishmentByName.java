package com.codexmind.establishment.usecases.establishment;


import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetEstblishmentByName {

    private final EstablishmentRepository establishmentRepository;

    public List<Establishment> execute(String name){

        var establishment = establishmentRepository.findEstablishmentByName(name, Status.ACTIVE);
        return establishment.isEmpty() ? null : establishment;
    }
}
