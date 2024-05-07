package com.codexmind.establishment.usecases.establishment;


import com.codexmind.establishment.domain.Address;
import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.dto.EstablishmentDTO;
import com.codexmind.establishment.repository.CustomerRepository;
import com.codexmind.establishment.repository.EstablishmentRepository;
import com.codexmind.establishment.service.ServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddFavoriteEstablishment {

    private final ServiceClient serviceClient;

    private final EstablishmentRepository repository;

    private final CustomerRepository customerRepository;

    public void execute(Set<Integer> establishmentIds, Integer customerId){

         var customer = customerRepository.findById(customerId).orElseThrow();
         for (Integer id: establishmentIds ){
             var establishment = repository.findById(id).get();
             establishment.setCustomer(customer);
             customer.getFavorites().add(establishment);
         }

         customerRepository.save(customer);
         log.info("favoritos salvos" , customer.getFavorites());
    }
}
