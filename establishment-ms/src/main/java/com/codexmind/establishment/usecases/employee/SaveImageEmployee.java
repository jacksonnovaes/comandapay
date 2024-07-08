package com.codexmind.establishment.usecases.employee;

import com.codexmind.establishment.exceptions.AuthorizationException;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.EmployeeRepository;
import com.codexmind.establishment.service.UserService;
import com.codexmind.establishment.usecases.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
@Service
@RequiredArgsConstructor
public class SaveImageEmployee {
    private final S3Service s3;

    private final EmployeeRepository employeeRepository;

    public URI execute(MultipartFile file, Integer id){
        var user = UserService.authenticated();
        if(user==null){
            throw new AuthorizationException("Access denied!");
        }


        var employee = employeeRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Employee nao encontrado!"));
        var uri = s3.upload(file, employee.getName()+employee.getLastName());
        employee.setUrlImage(uri.toString());
        employeeRepository.save(employee);

        return uri;
    }
}
