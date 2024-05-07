package com.codexmind.establishment.usecases.employee;


import com.codexmind.establishment.domain.Employee;
import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.domain.enums.Profile;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.exceptions.AuthorizationException;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.EmployeeRepository;
import com.codexmind.establishment.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DetailEmployee {

    private final EmployeeRepository employeeRepository;

    public Employee execute(Integer id){

        var userSS = UserService.authenticated();
        if(userSS != null || userSS.hasRole(Profile.ADMIN)
                || userSS.hasRole(Profile.EMPLOYEE) && !id.equals(userSS.getId())){
            throw new AuthorizationException("Access Denied!");
        }
        return employeeRepository.findByStatusAndId(Status.ACTIVE, id)
                .orElseThrow(()-> new EntityNotFoundException("Object Not Found for remove! id:" + id + " type:" + Establishment.class.getName()));

    }
}
