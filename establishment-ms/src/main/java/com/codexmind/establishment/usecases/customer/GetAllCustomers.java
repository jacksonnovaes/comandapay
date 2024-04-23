package com.codexmind.establishment.usecases.customer;

import com.codexmind.establishment.converters.CustomerConverter;
import com.codexmind.establishment.domain.Customer;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GetAllCustomers {

    private final CustomerRepository personRepository;

    public Page<Customer> execute(Integer page, Integer linesPerPge, String ordeby, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPge, Sort.Direction.valueOf(direction), ordeby);
        return personRepository.findByStatus(pageRequest, Status.ACTIVE).map(CustomerConverter::toCustomerEntity);
    }

}
