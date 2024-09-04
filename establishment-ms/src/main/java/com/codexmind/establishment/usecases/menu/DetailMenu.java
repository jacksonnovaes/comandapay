package com.codexmind.establishment.usecases.menu;


import com.codexmind.establishment.domain.Menu;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailMenu {


    private final MenuRepository repository;

    public Menu execute(Integer id){
        var menu = repository.findByIdAndStatus(id, Status.ACTIVE);
        return menu.orElseThrow(()-> new EntityNotFoundException(
                "Object Not Found! id:  "+ id + " type: "+ Menu.class.getName()
        ));

    }
}
