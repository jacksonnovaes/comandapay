package com.codexmind.establishment.usecases.menu;


import com.codexmind.establishment.domain.Menu;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteMenu {

    private final MenuRepository menuRepository;

    public Menu execute(Long id) {
        var menu = menuRepository.findByIdAndStatus(id, Status.ACTIVE);
        if (menu.isPresent()) {
            menu.get().setStatus(Status.INACTIVE);
            menuRepository.save(menu.get());
        }
        return menu.orElseThrow(() -> new EntityNotFoundException(
                "Object Not Found for remove! id:  " + id + " type: " + Menu.class.getName()
        ));

    }
}
