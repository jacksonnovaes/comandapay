package com.codexmind.establishment.usecases.establishment;

import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.repository.CustomerRepository;
import com.codexmind.establishment.repository.EstablishmentRepository;
import com.codexmind.establishment.service.ServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddFavoriteEstablishment {

    private final ServiceClient serviceClient;
    private final EstablishmentRepository establishmentRepository;
    private final CustomerRepository customerRepository;

    public Establishment execute(Integer establishmentId, Integer customerId) {
        Establishment establishment = establishmentRepository.findById(establishmentId)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado com o ID: " + establishmentId));

        // Recupera o cliente
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + customerId));

        if (customer.getFavorites().contains(establishment)) {
            log.warn("O estabelecimento já está na lista de favoritos do cliente: {}", customerId);

            customer.getFavorites().remove(establishment);
            establishment.setIsFavorite(Boolean.FALSE);
            establishment.setCustomer(null);
        }else{
            customer.getFavorites().add(establishment);
            establishment.setIsFavorite(Boolean.TRUE);
            establishment.setCustomer(customer);
        }

        customerRepository.save(customer);
        var savedEstablishment = establishmentRepository.save(establishment);
        log.info("Estabelecimento adicionado como favorito para o cliente: {}", customer.getId());

        log.warn("O estabelecimento já está na lista de favoritos do cliente: {}", customerId);


        return savedEstablishment;
    }
}
