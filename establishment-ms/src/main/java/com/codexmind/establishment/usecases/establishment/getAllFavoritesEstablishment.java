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
public class getAllFavoritesEstablishment {

    private final EstablishmentRepository establishmentRepository;

    public List<Establishment> execute(Integer idCustomer) {
        List<Establishment> favorites = establishmentRepository.findFavorites(idCustomer, Status.ACTIVE);

        if (favorites.isEmpty()) {
            throw new EntityNotFoundException("Nenhum estabelecimento favorito encontrado para o cliente com ID: " + idCustomer);
        }
        return favorites;

    }
}
