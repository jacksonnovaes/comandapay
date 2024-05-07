package com.codexmind.establishment.usecases.employee;


import com.codexmind.establishment.domain.Employee;
import com.codexmind.establishment.dto.saveUpdate.UpdateEmployeeDTO;
import com.codexmind.establishment.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateEmployee {

    private final EmployeeRepository employeeRepository;


    public Employee execute(UpdateEmployeeDTO updateEmployeeDTO, Integer id) {
        Employee employeeFind;
        employeeFind = employeeRepository.findById(id).get();

        Employee employee = Employee.builder()
                .id(id)
                .name(updateEmployeeDTO.getName() != null ? updateEmployeeDTO.getName() : employeeFind.getName())
                .lastName(updateEmployeeDTO.getLastName() != null ? updateEmployeeDTO.getLastName() : employeeFind.getLastName())
                .phone(updateEmployeeDTO.getPhone() != null ? updateEmployeeDTO.getPhone() : employeeFind.getPhone())
                .celPhone(updateEmployeeDTO.getCelPhone() != null ? updateEmployeeDTO.getCelPhone() : employeeFind.getCelPhone())
                .admissionDate(updateEmployeeDTO.getAdmissionDate() != null ? updateEmployeeDTO.getAdmissionDate() : employeeFind.getAdmissionDate())
                .build();

        return employeeRepository.save(employee);

    }
}
