package com.codexmind.establishment.usecases.employee;

import com.codexmind.establishment.converters.EmployeeConverter;
import com.codexmind.establishment.domain.Employee;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class GetAllEmployee {

    private final EmployeeRepository personRepository;

    public Page<Employee> execute(Integer page, Integer linesPerPge, String ordeBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPge, Sort.Direction.valueOf(direction), ordeBy);
        return personRepository.findByStatus(pageRequest, Status.ACTIVE).map(EmployeeConverter::toEmployeeEntity);
    }


}
