package com.codexmind.establishment.usecases.establishment;

import com.codexmind.establishment.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class GetImage {

    private static final String IMAGE_DIRECTORY = "home/jackson-devhub/projects-backend/comandapay/establishment-ms/images/up/";

    private final EstablishmentRepository establishmentRepository;

    public Resource execute(Integer id) {

        var establishment  = establishmentRepository.findById(id).orElseThrow();
        try {
            Path imagePath = Paths.get(IMAGE_DIRECTORY + establishment.getFileName());
            Resource imageResource = new UrlResource(imagePath.toUri());
            if (!imageResource.exists()) {
                imageResource.isReadable();
                return imageResource;
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
