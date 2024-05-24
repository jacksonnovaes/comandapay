package com.codexmind.establishment.converters;

import com.codexmind.establishment.domain.Menu;
import com.codexmind.establishment.dto.MenuDTO;

public class MenuConverter {
    public static MenuDTO toDTO(Menu menu) {
        return MenuDTO.builder()
                .id(menu.getId())
                .name(menu.getName())
                .establishment(menu.getEstablishment().getId())
                .build();
    }
}
