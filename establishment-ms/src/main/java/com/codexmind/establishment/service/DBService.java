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

    public DBService(
            EstablishmentRepository establishmentRepository,
            MenuRepository menuRepository,
            ProductRepository productRepository,
            EmployeeRepository employeeRepository,
            ServiceClient serviceClient,
            PasswordEncoder encoder,
            UserRepository userRepository,
            CustomerRepository customerRepository
    ) {
        this.establishmentRepository = establishmentRepository;
        this.menuRepository = menuRepository;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
        this.serviceClient = serviceClient;
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    public void instantiateDataBase() {
        // Address for the first establishment
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

        // Address for the second establishment
        var addressDTO2 = serviceClient.buscaEnderecoPorCep("02969070");
        var address2 = Address.builder()
                .name(addressDTO2.logradouro())
                .number("307")
                .complemento("")
                .bairro(addressDTO2.bairro())
                .city(addressDTO2.localidade())
                .postalCode(addressDTO2.cep())
                .uf(addressDTO2.uf())
                .build();

        // Creating menus
        var menu1 = Menu.builder()
                .name("Cervejas")
                .status(Status.ACTIVE)
                .build();

        var menu2 = Menu.builder()
                .name("Drinks")
                .status(Status.ACTIVE)
                .build();

        var menu3 = Menu.builder()
                .name("Alimentícios")
                .status(Status.ACTIVE)
                .build();
        var menu4 = Menu.builder()
                .name("Refrigerantes")
                .status(Status.ACTIVE)
                .build();


        // Creating products
        var product1 = Product.builder()
                .name("Coca Cola")
                .price(new BigDecimal("5.00"))
                .menu(menu2)
                .estoque(50)
                .build();

        var product2 = Product.builder()
                .name("Skol 200 ml")
                .price(new BigDecimal("15.00"))
                .menu(menu1)
                .build();

        var product3 = Product.builder()
                .name("Porção de Batata Frita")
                .price(new BigDecimal("20.00"))
                .menu(menu3)
                .build();

        var product4 = Product.builder()
                .name("Fanta")
                .price(new BigDecimal("5.00"))
                .menu(menu2)
                .build();

        var product5 = Product.builder()
                .name("Heineken 350 ml")
                .price(new BigDecimal("12.00"))
                .menu(menu1)
                .estoque(50)
                .build();

        var product6 = Product.builder()
                .name("Caipirinha")
                .price(new BigDecimal("18.00"))
                .menu(menu2)
                .build();

        var product7 = Product.builder()
                .name("Hambúrguer")
                .price(new BigDecimal("25.00"))
                .menu(menu3)
                .estoque(50)
                .build();

        var product8 = Product.builder()
                .name("Água Mineral")
                .price(new BigDecimal("3.00"))
                .menu(menu2)
                .estoque(50)
                .build();

        var product9 = Product.builder()
                .name("Porção de Calabresa")
                .price(new BigDecimal("30.00"))
                .menu(menu3)
                .build();

        var product10 = Product.builder()
                .name("Budweiser 350 ml")
                .price(new BigDecimal("10.00"))
                .menu(menu1)
                .estoque(50)
                .build();

        var product11 = Product.builder()
                .name("Refrigerante Guaraná")
                .price(new BigDecimal("5.00"))
                .estoque(50)
                .menu(menu2)
                .build();

        var product12 = Product.builder()
                .name("Porção de Queijo")
                .price(new BigDecimal("25.00"))
                .estoque(50)
                .menu(menu3)
                .build();

        var product13 = Product.builder()
                .name("Smirnoff Ice")
                .price(new BigDecimal("15.00"))
                .estoque(50)
                .menu(menu1)
                .build();

        var product14 = Product.builder()
                .name("Suco Natural")
                .price(new BigDecimal("7.00"))
                .menu(menu2)
                .build();

        // Setting products to menus
        menu1.setProducts(Arrays.asList(product2, product5, product10, product13));
        menu2.setProducts(Arrays.asList(product1, product4, product6, product8, product11, product14));
        menu3.setProducts(Arrays.asList(product3, product7, product9, product12));

        // Creating establishments
        var establishment1 = Establishment.builder()
                .name("Bar I")
                .cnpj("0862537263200001-42")
                .menus(Arrays.asList(menu1, menu2, menu3))
                .status(Status.ACTIVE)
                .rate(5F)
                .isFavorite(Boolean.FALSE)
                .address(address)
                .build();

        var establishment2 = Establishment.builder()
                .name("Bar II")
                .cnpj("0862537263200001-43")
                .menus(Arrays.asList(menu1))
                .status(Status.ACTIVE)
                .rate(5F)
                .isFavorite(Boolean.FALSE)
                .address(address2)
                .build();

        // Setting establishment for menus
        menu1.setEstablishment(establishment1);
        menu2.setEstablishment(establishment1);
        menu3.setEstablishment(establishment1);

        // Creating employees
        var employee1 = Employee.builder()
                .name("Jackson")
                .lastName("Bispo")
                .cpf("39219796848")
                .status(Status.ACTIVE)
                .phone("991556628")
                .celPhone("991556628")
                .establishment(establishment1)
                .addressList(Arrays.asList(address))
                .admissionDate(LocalDate.now())
                .build();

        var employee2 = Employee.builder()
                .name("Ana")
                .lastName("Silva")
                .cpf("39219796849")
                .status(Status.ACTIVE)
                .phone("991556629")
                .celPhone("991556629")
                .establishment(establishment1)
                .addressList(Arrays.asList(address))
                .admissionDate(LocalDate.now())
                .build();

        // Creating customers
        var customer1 = Customer.builder()
                .name("Jackson")
                .lastName("Bispo")
                .cpf("39219796848")
                .status(Status.ACTIVE)
                .phone("991556628")
                .celPhone("991556628")
                .birthDate(LocalDate.of(1998, 6, 4))
                .build();

        var customer2 = Customer.builder()
                .name("Maria")
                .lastName("Oliveira")
                .cpf("39219796850")
                .status(Status.ACTIVE)
                .phone("991556630")
                .celPhone("991556630")
                .birthDate(LocalDate.of(1990, 8, 14))
                .build();

        // Creating users
        var userCustomer1 = User.builder()
                .login("jackson.novaes@gmail.com")
                .pass(encoder.encode("11223344"))
                .roles(Set.of(Profile.CLIENT.getDescription()))
                .build();

        var userCustomer2 = User.builder()
                .login("maria.oliveira@gmail.com")
                .pass(encoder.encode("11223344"))
                .roles(Set.of(Profile.CLIENT.getDescription()))
                .build();

        var userEmployee1 = User.builder()
                .login("jackson.novaes@live.com")
                .pass(encoder.encode("11223344"))
                .roles(Set.of(Profile.ADMIN.getDescription()))
                .build();

        var userEmployee2 = User.builder()
                .login("ana.silva@live.com")
                .pass(encoder.encode("11223344"))
                .roles(Set.of(Profile.ADMIN.getDescription()))
                .build();

        // Setting relationships
        establishment1.setEmployees(Arrays.asList(employee1, employee2));
        customer1.setProfiles(Set.of(Profile.CLIENT.getCod()));
        customer1.setAddressList(Arrays.asList(address));
        customer1.setUser(userCustomer1);
        customer2.setProfiles(Set.of(Profile.CLIENT.getCod()));
        customer2.setAddressList(Arrays.asList(address2));
        customer2.setUser(userCustomer2);
        employee1.setUser(userEmployee1);
        employee1.setAddressList(Arrays.asList(address));
        employee1.setProfiles(Set.of(Profile.CLIENT.getCod()));
        employee2.setUser(userEmployee2);
        employee2.setAddressList(Arrays.asList(address));
        employee2.setProfiles(Set.of(Profile.CLIENT.getCod()));

        // Saving data to repositories
        userRepository.saveAll(Arrays.asList(userEmployee1, userEmployee2, userCustomer1, userCustomer2));
        menuRepository.saveAll(Arrays.asList(menu1, menu2, menu3));
        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11, product12, product13, product14));
        employeeRepository.saveAll(Arrays.asList(employee1, employee2));
        establishmentRepository.saveAll(Arrays.asList(establishment1, establishment2));
        customerRepository.saveAll(Arrays.asList(customer1, customer2));
    }
}
