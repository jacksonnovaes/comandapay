package com.codexmind.establishment.usecases.establishment;


import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.EstablishmentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteEstablishment {

    private final EstablishmentRepository establishmentRepository;

    public Establishment execute(Integer id) {
        var establishment = establishmentRepository.findByIdAndStatus(id, Status.ACTIVE);
        if (establishment.isPresent()) {
            establishment.get().setStatus(Status.INACTIVE);
            establishmentRepository.save(establishment.get());
        }
        return establishment.orElseThrow(() -> new EntityNotFoundException(
                "Object Not Found for remove! id:  " + id + " type: " + Establishment.class.getName()
        ));

    }
}
