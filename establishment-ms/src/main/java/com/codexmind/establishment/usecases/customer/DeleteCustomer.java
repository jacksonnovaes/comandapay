package com.codexmind.establishment.usecases.customer;

import com.codexmind.establishment.converters.CustomerConverter;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCustomer {


    private final CustomerRepository customerRepository;

    private final DetailCustomer detailCustomer;
    public boolean execute(Integer id) {
        var customer = detailCustomer.execute(id);
        if (customer != null && customer.getStatus().equals(Status.ACTIVE)) {
            customer.setStatus(Status.INACTIVE);

            customerRepository.save(CustomerConverter.toEntity(customer));
            return customer.getStatus().equals(Status.INACTIVE);
        }
        return false;
    }
}
