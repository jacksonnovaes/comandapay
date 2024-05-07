package com.codexmind.establishment.converters;

import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.dto.EstablishmentDTO;
import com.codexmind.establishment.dto.ResponseEstablishmentDTO;

import java.util.ArrayList;
import java.util.List;

public class EstablishmentConverter {
    public static EstablishmentDTO toDTO(Establishment establishment) {
        return new EstablishmentDTO(
                establishment.getName(),
                establishment.getCnpj(),
                establishment.getAddress().getName(),
                establishment.getAddress().getNumber(),
                establishment.getAddress().getComplemento(),
                establishment.getAddress().getPostalCode(),
                establishment.getAddress().getBairro(),
                establishment.getAddress().getCity(),
                establishment.getAddress().getUf()
        );
    }
        public static ResponseEstablishmentDTO toResponseDTO(Establishment establishment) {
            return new ResponseEstablishmentDTO(
                    establishment.getName(),
                    establishment.getCnpj()
            );
        }

    public static List<EstablishmentDTO> toListDTO(List<Establishment> establishments) {
        List<EstablishmentDTO> establishmentDTOs = new ArrayList<>();
        for (Establishment establishment : establishments) {
            EstablishmentDTO establishmentDTO = new EstablishmentDTO(
                    establishment.getName(),
                    establishment.getCnpj(),
                    establishment.getAddress().getName(),
                    establishment.getAddress().getNumber(),
                    establishment.getAddress().getComplemento(),
                    establishment.getAddress().getPostalCode(),
                    establishment.getAddress().getBairro(),
                    establishment.getAddress().getCity(),
                    establishment.getAddress().getUf()
            );
            establishmentDTOs.add(establishmentDTO);
        }
        return establishmentDTOs;
    }
}
