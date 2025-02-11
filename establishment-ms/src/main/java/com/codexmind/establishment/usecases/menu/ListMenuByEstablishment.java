package com.codexmind.establishment.usecases.menu;

import com.codexmind.establishment.domain.Menu;
import com.codexmind.establishment.repository.EmployeeRepository;
import com.codexmind.establishment.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListMenuByEstablishment {

    private final MenuRepository repository;

    private final EmployeeRepository employeeRepository;

    public List<Menu> execute(Integer id) {

        var employee = employeeRepository.findById(id);
        System.out.println();
        return repository.findMenusByEstablishmenteId(employee.get().getEstablishment().getId());
    }
}
