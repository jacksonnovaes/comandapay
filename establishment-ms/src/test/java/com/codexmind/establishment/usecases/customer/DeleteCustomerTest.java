package com.codexmind.establishment.usecases.customer;

import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.dto.CustomerDTO;
import com.codexmind.establishment.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DeleteCustomerTest {



    @InjectMocks
    private DeleteCustomer deleteCustomer;

    @Mock
    private DetailCustomer detailCustomer;

    @Mock
    private CustomerRepository repository;


    @Test
     void shouldDeleteAcustomer(){
        var customerDTO = CustomerDTO.builder()
                .name("jackson")
                .lastName("Bispo")
                .cpf("39219796848")
                .status(Status.ACTIVE)
                .phone("991556628")
                .celPhone("991556628")
                .build();

        var id = 1L;
        when(detailCustomer.execute(id)).thenReturn(customerDTO);

        boolean result  = deleteCustomer.execute(id);

        assertTrue(result);
        assertEquals(Status.INACTIVE, customerDTO.getStatus());
        verify(repository).save(any());
    }


    @Test
    void testExecuteWhenCustomerIsNull() {
        Long customerId = 1L;

        when(detailCustomer.execute(customerId)).thenReturn(null);

        boolean result = deleteCustomer.execute(customerId);

        assertFalse(result);
        verify(repository, never()).save(any());
    }

    @Test
    void testExecuteWhenCustomerIsInactive() {
        Long customerId = 1L;
        var customerDTO = CustomerDTO.builder()
                .name("jackson")
                .lastName("Bispo")
                .cpf("39219796848")
                .status(Status.INACTIVE)
                .phone("991556628")
                .celPhone("991556628")
                .build();

        when(detailCustomer.execute(customerId)).thenReturn(customerDTO);

        boolean result = deleteCustomer.execute(customerId);

        assertFalse(result);
        verify(repository, never()).save(any());
    }

}
