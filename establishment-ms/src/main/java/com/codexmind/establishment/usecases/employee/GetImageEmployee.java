package com.codexmind.establishment.usecases.employee;

import com.codexmind.establishment.domain.Employee;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetImageEmployee {

    private final EmployeeRepository employeeRepository;


    public Optional<Employee> execute(Integer id){

        return employeeRepository.findImageByID(Status.ACTIVE, id);
    }
}
