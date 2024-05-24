package com.codexmind.establishment.usecases.menu;

import com.codexmind.establishment.domain.Menu;
import com.codexmind.establishment.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListMenuByEstablishment {

    private final MenuRepository repository;

    public List<Menu> execute(Integer id){
        return  repository.findMenusByEstablishmenteId(id);
    }
}
