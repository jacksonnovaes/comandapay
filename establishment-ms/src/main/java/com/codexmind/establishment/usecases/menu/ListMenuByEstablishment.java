package com.codexmind.establishment.usecases.menu;

import com.codexmind.establishment.domain.Menu;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.repository.EmployeeRepository;
import com.codexmind.establishment.repository.EstablishmentRepository;
import com.codexmind.establishment.repository.MenuRepository;
import com.codexmind.establishment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListMenuByEstablishment {

    private final MenuRepository repository;

    private final EmployeeRepository employeeRepository;

    public List<Menu> execute(Integer id){
        return  repository.findMenusByEstablishmenteId(id);
    }
}
