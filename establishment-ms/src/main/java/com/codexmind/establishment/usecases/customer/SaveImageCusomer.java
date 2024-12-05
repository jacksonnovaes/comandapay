package com.codexmind.establishment.usecases.customer;

import com.codexmind.establishment.exceptions.AuthorizationException;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.CustomerRepository;
import com.codexmind.establishment.service.UserService;
import com.codexmind.establishment.usecases.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class SaveImageCusomer {
    private final S3Service s3;

    private final CustomerRepository customerRepository;

    public URI execute(MultipartFile file, Integer id) {
        var user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Access denied!");
        }


        var employee = customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cliente nao encontrado!"));
        var uri = s3.upload(file, employee.getName() + employee.getLastName());
        employee.setUrlImage(uri.toString());

        customerRepository.save(employee);

        return uri;
    }
}
