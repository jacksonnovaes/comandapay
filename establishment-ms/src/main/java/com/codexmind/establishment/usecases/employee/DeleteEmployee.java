package com.codexmind.establishment.usecases.employee;

import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.repository.EmployeeRepository;
import com.codexmind.establishment.usecases.customer.DetailCustomer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteEmployee {


    private final EmployeeRepository employeeRepository;

    private final DetailCustomer detailCustomer;

    public boolean execute(Integer id) {
        var employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employee.get().setStatus(Status.INACTIVE);

            employeeRepository.save(employee.get());
            return employee.get().getStatus().equals(Status.INACTIVE);
        }
        return false;
    }
}
