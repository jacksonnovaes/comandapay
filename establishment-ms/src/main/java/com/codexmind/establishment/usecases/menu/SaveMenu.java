package com.codexmind.establishment.usecases.menu;


import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.domain.Menu;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.dto.MenuDTO;
import com.codexmind.establishment.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaveMenu {


    private MenuRepository repository;

    public Menu execute(MenuDTO saveMenuDTO) {
        var establishmente = Establishment.builder()
                .id(saveMenuDTO.establishment)
                .build();
        var menu = Menu.builder()
                .name(saveMenuDTO.getName())
                .establishment(establishmente)
                .status(Status.ACTIVE)
                .build();
        return repository.save(menu);
    }
}
