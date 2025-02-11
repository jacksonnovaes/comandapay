package com.codexmind.establishment.usecases.establishment;


import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.EstablishmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DetailEstablishmentByUser {

    private final EstablishmentRepository establishmentRepository;

    public Establishment execute(Integer id) {

        var establishment = establishmentRepository.getEstablishmentByEmployee(id);
        return establishment.orElseThrow(() -> new EntityNotFoundException(
                "Object Not Found! id:  " + id + " type: " + Establishment.class.getName()
        ));
    }
}
