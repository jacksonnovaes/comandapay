package com.codexmind.establishment.usecases.menu;


import com.codexmind.establishment.domain.Menu;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DetailMenu {


    private MenuRepository repository;

    public Menu execute(Integer id){
        var menu = repository.findByIdAndStatus(id, Status.ACTIVE);
        return menu.orElseThrow(()-> new EntityNotFoundException(
                "Object Not Found! id:  "+ id + " type: "+ Menu.class.getName()
        ));

    }
}
