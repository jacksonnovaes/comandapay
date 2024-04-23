package com.codexmind.establishment.usecases.establishment;


import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.dto.EstablishmentDTO;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.EstablishmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateEstablishment {

    private final EstablishmentRepository establishmentRepository;

    public Establishment execute(Long id, EstablishmentDTO establishmentDTO) {

        var establishmentFind = establishmentRepository.findById(id);

        if(establishmentFind.isPresent()) {
            var establishment = Establishment.builder()
                    .id(id)
                    .name(establishmentDTO.name() != null ? establishmentDTO.name() : establishmentFind.get().getName())
                    .cnpj(establishmentDTO.cnpj() != null ? establishmentDTO.cnpj() : establishmentFind.get().getCnpj())
                    .status(Status.ACTIVE)
                    .address(establishmentFind.get().getAddress())
                    .menus(establishmentFind.get().getMenus())
                    .build();
            establishmentRepository.save(establishment);
        }
        return establishmentFind.orElseThrow(() -> new EntityNotFoundException(
                "Object Not Found! id:  " + id + " type: " + Establishment.class.getName()));


    }
}
