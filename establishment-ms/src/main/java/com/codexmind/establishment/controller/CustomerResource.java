package com.codexmind.establishment.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.codexmind.establishment.converters.CustomerConverter;
import com.codexmind.establishment.dto.CustomerDTO;
import com.codexmind.establishment.dto.PersonDTO;
import com.codexmind.establishment.dto.saveUpdate.SaveCustomerDTO;
import com.codexmind.establishment.dto.saveUpdate.UpdateCustomerDTO;
import com.codexmind.establishment.usecases.customer.DeleteCustomer;
import com.codexmind.establishment.usecases.customer.DetailCustomer;
import com.codexmind.establishment.usecases.customer.GetAllCustomers;
import com.codexmind.establishment.usecases.customer.SaveCustomer;
import com.codexmind.establishment.usecases.customer.UpdateCustomer;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping({"/api/v1/customer", "/api/v1/signup"})

@CrossOrigin(origins = "*")
public class CustomerResource {

    private final SaveCustomer saveCustomer;

    private final UpdateCustomer updateCustomer;

    private final GetAllCustomers getAllCustomers;

    private final DetailCustomer detailCustomer;

    private final DeleteCustomer deleteCustomer;

    public CustomerResource(SaveCustomer saveCustomer, UpdateCustomer updateCustomer, GetAllCustomers getAllCustomers, DetailCustomer detailCustomer, DeleteCustomer deleteCustomer) {
        this.saveCustomer = saveCustomer;
        this.updateCustomer = updateCustomer;
        this.getAllCustomers = getAllCustomers;
        this.detailCustomer = detailCustomer;
        this.deleteCustomer = deleteCustomer;
    }


    @SecurityRequirement(name = "bearer-key")
    @PostMapping(value = "/save")
    public ResponseEntity<PersonDTO> saveCustomer(@RequestBody @Valid SaveCustomerDTO customerDTO,
                                                  UriComponentsBuilder uriBuilder) {
        var customer = saveCustomer.execute(customerDTO);
        var uri = uriBuilder.path("/customer/save/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(CustomerConverter.toDTO(customer));
    }



    @SecurityRequirement(name = "bearer-key")
    @GetMapping(value = "/customers")
    public ResponseEntity<Page<CustomerDTO>> listCustomer(
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24")Integer linesPerPge,
            @RequestParam(value = "order",defaultValue = "name")String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC")String direction
            ) {
        var list
                = getAllCustomers.execute(page,
                linesPerPge,
                orderBy,
                direction).map(CustomerConverter::toDTO);
        return ResponseEntity.ok(list);
    }

    @SecurityRequirement(name = "bearer-key")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> detailCustomer(@PathVariable Long id) {
        var customer = detailCustomer.execute(id);
        return ResponseEntity.ok(customer);
    }

    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        var isRemoved = deleteCustomer.execute(id);
        if (isRemoved) {
            return ResponseEntity.status(HttpStatus.OK).body("Cliente excluído com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }

    @SecurityRequirement(name = "bearer-key")
    @PutMapping(value = "/{id}")
    public ResponseEntity<UpdateCustomerDTO> update(@PathVariable Long id,
                                                    @RequestBody @Valid UpdateCustomerDTO updateCustomerDTO,
                                                    UriComponentsBuilder uriBuilder) {
        updateCustomerDTO.setId(id);
        var customer =updateCustomer.execute(updateCustomerDTO);
        var uri = uriBuilder.path("/customer/update/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(CustomerConverter.toUpdateDTO(customer));
    }
}
