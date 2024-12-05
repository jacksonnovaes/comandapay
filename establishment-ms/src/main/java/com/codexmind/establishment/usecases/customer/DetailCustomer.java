package com.codexmind.establishment.usecases.customer;

import com.codexmind.establishment.converters.CustomerConverter;
import com.codexmind.establishment.domain.Customer;
import com.codexmind.establishment.domain.enums.Profile;
import com.codexmind.establishment.dto.CustomerDTO;
import com.codexmind.establishment.exceptions.AuthorizationException;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.CustomerRepository;
import com.codexmind.establishment.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DetailCustomer {

    private final CustomerRepository customerRepository;


    public CustomerDTO execute(Integer id) {
        var userSS = UserService.authenticated();
        if (userSS == null || userSS.hasRole(Profile.ADMIN)
                || userSS.hasRole(Profile.EMPLOYEE)
                && !id.equals(userSS.getId())) {
            throw new AuthorizationException("Access Denied!");
        }

        var employee = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Object Not Found for remove! id:  " + id + " type: " + Customer.class.getName()));
        return CustomerConverter.toDTO(employee);
    }
}
