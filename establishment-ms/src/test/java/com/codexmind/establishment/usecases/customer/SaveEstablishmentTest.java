package com.codexmind.establishment.usecases.customer;


import com.codexmind.establishment.dto.AddressDTO;
import com.codexmind.establishment.fixture.EstablishmentFixture;
import com.codexmind.establishment.repository.EstablishmentRepository;
import com.codexmind.establishment.service.ServiceClient;
import com.codexmind.establishment.usecases.establishment.SaveEstablishment;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
public class SaveEstablishmentTest {

    @InjectMocks
    private SaveEstablishment saveEstablishment;

    @Mock
    private EstablishmentRepository repository;

    @Mock
    private ServiceClient serviceClient;

    @Test
    void shouldSaveEstablishmentTest(){

        var addressDTO = new AddressDTO(
                "05077010",
                "av ernesto Igel",
                "apot 45",
                "Lapa",
                "Sao paulo",
                "SP"
        );
        var establishment = EstablishmentFixture.savedEstablishment();

        when(serviceClient.buscaEnderecoPorCep(addressDTO.cep())).thenReturn(addressDTO);
        when(repository.save(Mockito.any())).thenReturn(establishment);



    }


}
