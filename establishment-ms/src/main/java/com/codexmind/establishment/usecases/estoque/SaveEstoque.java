package com.codexmind.establishment.usecases.estoque;

import com.codexmind.establishment.domain.Estoque;
import com.codexmind.establishment.repository.EstablishmentRepository;
import com.codexmind.establishment.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveEstoque {

    private final EstoqueRepository estoqueRepository;

    public Estoque execute(Estoque estoque){
        return estoqueRepository.save(estoque);
    }
}
