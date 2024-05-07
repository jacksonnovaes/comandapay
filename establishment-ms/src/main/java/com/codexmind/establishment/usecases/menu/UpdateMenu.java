package com.codexmind.establishment.usecases.menu;


import com.codexmind.establishment.domain.Menu;
import com.codexmind.establishment.dto.MenuDTO;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateMenu {


    private MenuRepository repository;

    public Menu execute(Integer id, MenuDTO menuDTO){

        var menuFound = repository.findById(id);
        if(menuFound.isPresent()) {
            var menu = Menu.builder()
                    .id(id)
                    .name(menuDTO.getName() != null ? menuDTO.getName() : menuFound.get().getName())
                    .build();
            repository.save(menu);
        }
        return menuFound.orElseThrow(() -> new EntityNotFoundException(
                "Object Not Found! id:  " + id + " type: " + Menu.class.getName()));

    }
}
