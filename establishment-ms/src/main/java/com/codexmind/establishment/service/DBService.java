package com.codexmind.establishment.service;


import com.codexmind.establishment.domain.*;
import com.codexmind.establishment.domain.enums.Profile;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

@Service
public class DBService {

    private final EstablishmentRepository establishmentRepository;


    private final MenuRepository menuRepository;
    private final ProductRepository productRepository;

    private final EmployeeRepository employeeRepository;

    private final ServiceClient serviceClient;

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    public DBService(EstablishmentRepository establishmentRepository, MenuRepository menuRepository, ProductRepository productRepository, EmployeeRepository employeeRepository, ServiceClient serviceClient, PasswordEncoder encoder, UserRepository userRepository, CustomerRepository customerRepository) {
        this.establishmentRepository = establishmentRepository;
        this.menuRepository = menuRepository;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
        this.serviceClient = serviceClient;
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    public void instantiateDataBase(){
        var addressDTO = serviceClient.buscaEnderecoPorCep("05077010");
        var address = Address.builder()
                .name(addressDTO.logradouro())
                .number("307")
                .complemento("")
                .bairro(addressDTO.bairro())
                .city(addressDTO.localidade())
                .postalCode(addressDTO.cep())
                .uf(addressDTO.uf())
                .build();

        var menu = Menu.builder()
                .name("Bebidas")
                .status(Status.ACTIVE)
                .build();

        var product = Product.builder()
                .name("cocal cola")
                .price(new BigDecimal("15.00"))
                .menu(menu)
                .build();
        var product2 = Product.builder()
                .name("skol 200 ml")
                .price(new BigDecimal("15.00"))
                .menu(menu)
                .build();

        var product3 = Product.builder()
                .name("Porcao ")
                .price(new BigDecimal("45.00"))
                .menu(menu)
                .build();
        menu.setProducts(Arrays.asList(product, product2));
        var establishment = Establishment.builder()
                .name("bar II")
                .cnpj("0862537263200001-43")
                .menus(Arrays.asList(menu))
                .status(Status.ACTIVE)
                .address(address)
                .build();
        menu.setEstablishment(establishment);
        var employee = Employee.builder()
                .name("jackson")
                .lastName("Bispo")
                .cpf("39219796848")
                .status(Status.ACTIVE)
                .phone("991556628")
                .celPhone("991556628")
                .establishment(establishment)
                .addressList(Arrays.asList(address))
                .admissionDate(LocalDate.now())
                .build();
        var customer = Customer.builder()
                .name("jackson")
                .lastName("Bispo")
                .cpf("39219796848")
                .status(Status.ACTIVE)
                .phone("991556628")
                .celPhone("991556628")
                .birthDate(LocalDate.of(1998, 06, 04))
                .build();

        var userCustomer = User.builder()
                .login("jackson.novaes@gmail.com")
                .pass(encoder.encode("11223344"))
                .roles(Set.of(Profile.CLIENT.getDescription()))
                .build();

        var userEmployee = User.builder()
                .login("jackson.novaes@live.com")
                .pass(encoder.encode("11223344"))
                .roles(Set.of(Profile.ADMIN.getDescription()))
                .build();
        establishment.setEmployees(Arrays.asList(employee));
        customer.setProfiles(Set.of(Profile.CLIENT.getCod()));
        customer.setAddressList(Arrays.asList(address));
        customer.setUser(userCustomer);
        employee.setUser(userEmployee);
        employee.setAddressList(Arrays.asList(address));
        employee.setProfiles(Set.of(Profile.CLIENT.getCod()));
        userRepository.saveAll(Arrays.asList(userEmployee, userCustomer));
        menuRepository.saveAll(Arrays.asList(menu));
        productRepository.saveAll(Arrays.asList(product,product2, product3));
        employeeRepository.saveAll(Arrays.asList(employee));
        establishmentRepository.saveAll(Arrays.asList(establishment));
        customerRepository.saveAll(Arrays.asList(customer));
    }
}
