package com.codexmind.establishment.usecases.customer;


import com.codexmind.establishment.domain.Address;
import com.codexmind.establishment.domain.Customer;
import com.codexmind.establishment.domain.User;
import com.codexmind.establishment.domain.enums.Profile;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.dto.asaas.RequestSaveCustomerAsaasDTO;
import com.codexmind.establishment.dto.saveUpdate.SaveCustomerDTO;
import com.codexmind.establishment.repository.CustomerRepository;
import com.codexmind.establishment.repository.UserRepository;
import com.codexmind.establishment.service.SaveCustomerAsaasPay;
import com.codexmind.establishment.service.ServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class SaveCustomer {

    private final CustomerRepository personRepository;

    private final UserRepository userRepository;

    private final ServiceClient serviceClient;

    private final PasswordEncoder encoder;

    private final SaveCustomerAsaasPay saveCustomerAsaasPay;

    public Customer execute(SaveCustomerDTO customerDTO){

        var addressDTO = serviceClient.buscaEnderecoPorCep(customerDTO.postalCode());
        var asaasCustomer = new RequestSaveCustomerAsaasDTO(customerDTO.name(), customerDTO.cpfCnpj());
        var customerId = saveCustomerAsaasPay.createCustomer(asaasCustomer);

        var address = Address.builder()
                .name(addressDTO.logradouro())
                .number(customerDTO.number())
                .complemento(customerDTO.complemento())
                .postalCode(customerDTO.postalCode())
                .bairro(addressDTO.bairro())
                .city(addressDTO.localidade())
                .uf(customerDTO.uf())
                .build();

        var customer = Customer.builder()
                .id(null)
                .name(customerDTO.name())
                .lastName(customerDTO.lastName())
                .cpf(customerDTO.cpfCnpj())
                .phone(customerDTO.phone())
                .celPhone(customerDTO.celPhone())
                .status(Status.fromValue("ATIVO"))
                .birthDate(customerDTO.birthDate())
                .addressList(List.of(address))
                .profiles(Set.of(Profile.EMPLOYEE.getCod()))
                .customerId(customerId.id())
                .build();
        var user = User.builder()
                .login(customerDTO.login())
                .pass(encoder.encode(customerDTO.pass()))
                .build();
        customer.setUser(user);
        address.setCustomer(customer);
        userRepository.save(user);
        var customerSaved = personRepository.save(customer);


        return customerSaved;
    }

}
