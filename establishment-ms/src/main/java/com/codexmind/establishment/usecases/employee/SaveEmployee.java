package com.codexmind.establishment.usecases.employee;


import com.codexmind.establishment.domain.Address;
import com.codexmind.establishment.domain.Cargo;
import com.codexmind.establishment.domain.Employee;
import com.codexmind.establishment.domain.User;
import com.codexmind.establishment.domain.enums.Profile;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.dto.saveUpdate.SaveEmployeeDTO;
import com.codexmind.establishment.repository.AddressRepository;
import com.codexmind.establishment.repository.CargoRepository;
import com.codexmind.establishment.repository.EmployeeRepository;
import com.codexmind.establishment.repository.UserRepository;
import com.codexmind.establishment.service.ServiceClient;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class SaveEmployee {

    private final EmployeeRepository personRepository;

    private final CargoRepository cargoRepository;

    private final ServiceClient serviceClient;

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    private final PasswordEncoder encoder;

    public Employee execute(@Valid SaveEmployeeDTO employeeDTO) {

        var addressDTO = serviceClient.buscaEnderecoPorCep(employeeDTO.postalCode());

        Employee employee;

        var address = Address.builder()
                .name(addressDTO.logradouro())
                .number(employeeDTO.number())
                .complemento(employeeDTO.complemento())
                .postalCode(addressDTO.cep())
                .bairro(addressDTO.bairro())
                .city(addressDTO.localidade())
                .uf(addressDTO.uf())
                .build();

        employee = Employee.builder()
                .id(null)
                .name(employeeDTO.name())
                .lastName(employeeDTO.lastName())
                .cpf(employeeDTO.cpf())
                .phone(employeeDTO.phone())
                .celPhone(employeeDTO.celPhone())
                .status(Status.ACTIVE)
                .admissionDate(LocalDate.now())
                .addressList(List.of(address))
                .profiles(Set.of(Profile.EMPLOYEE_ESTABLISHMENT.getCod()))
                .build();

        address.setEmployee(employee);

        var newCargo = Cargo.builder()
                .name(employeeDTO.cargo())
                .build();
        var user = User.builder()
                .login(employeeDTO.login())
                .pass(encoder.encode(employeeDTO.pass()))
                .build();
        employee.setUser(user);
        employee.setCargo(newCargo);
        userRepository.save(user);
        cargoRepository.save(employee.getCargo());
        var person = personRepository.save(employee);
        addressRepository.saveAll(Arrays.asList(address));
        return person;
    }

}
