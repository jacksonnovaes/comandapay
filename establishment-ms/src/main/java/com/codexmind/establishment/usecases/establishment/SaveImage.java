package com.codexmind.establishment.usecases.establishment;

import com.codexmind.establishment.exceptions.AuthorizationException;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.EstablishmentRepository;
import com.codexmind.establishment.service.LocalImageService;
import com.codexmind.establishment.service.UserService;
import com.codexmind.establishment.usecases.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class SaveImage {

    private final EstablishmentRepository establishmentRepository;

    private final S3Service s3;

    private final LocalImageService imageService;

    public URI execute(MultipartFile file, Integer idEstablishment) {

        var user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Access denied!");
        }

        var establishment = establishmentRepository.findById(idEstablishment).orElseThrow(
                () -> new EntityNotFoundException("Establecimento nao encontrado!"));

        var uri = s3.upload(file, establishment.getName());
        establishment.setUrlImage(uri.toString());
        establishmentRepository.save(establishment);
        return uri;
    }
}
