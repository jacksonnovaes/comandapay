package com.codexmind.establishment.usecases.establishment;

import com.codexmind.establishment.repository.EstablishmentRepository;
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
    private static final String IMAGE_DIRECTORY = "/home/jackson-devhub/projects-backend/comandapay/establishment-ms/images/up/";

    public URI execute(MultipartFile file) {
        return s3.upload(file);
    }
}
