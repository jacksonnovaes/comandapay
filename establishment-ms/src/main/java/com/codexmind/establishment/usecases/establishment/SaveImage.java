package com.codexmind.establishment.usecases.establishment;

import com.codexmind.establishment.exceptions.AuthorizationException;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.EstablishmentRepository;
import com.codexmind.establishment.service.UserService;
import com.codexmind.establishment.usecases.S3Service;
import com.codexmind.establishment.usecases.imageService.ConvertImageToJpg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;

@Service
@RequiredArgsConstructor
public class SaveImage {

    private final EstablishmentRepository establishmentRepository;

    private final S3Service s3;

    public URI execute(MultipartFile file, Integer idEstablishment) {

        var user = UserService.authenticated();
        if(user==null){
            throw new AuthorizationException("Access denied!");
        }

        var uri =  s3.upload(file);
        var establishment = establishmentRepository.findById(idEstablishment).orElseThrow(
                ()-> new EntityNotFoundException("Establecimento nao encontrado!"));
        establishment.setUrlImage(uri.toString());
        establishmentRepository.save(establishment);
        return uri;
    }
}
