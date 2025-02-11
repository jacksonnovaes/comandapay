package com.codexmind.establishment.usecases.customer;

import com.codexmind.establishment.domain.Customer;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetImageCustomer {

    private final CustomerRepository customerRepository;


    public Optional<Customer> execute(Integer id) {

        return customerRepository.findImageByID(Status.ACTIVE, id);
    }
}
