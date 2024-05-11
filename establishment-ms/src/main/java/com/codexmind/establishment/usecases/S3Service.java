package com.codexmind.establishment.usecases;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.codexmind.establishment.exceptions.FileException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final Logger LOG = LoggerFactory.getLogger(S3Service.class);

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public URI upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream stream = null;
            stream = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            return upload(stream, fileName, contentType);
        } catch (IOException e) {
            throw new FileException("ERRO de IO" + e.getMessage());
        }


    }

    public URI upload(InputStream is, String fileName, String contentType) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            LOG.info("inicio do upload");
            amazonS3.putObject(bucketName, fileName, is, metadata);
            LOG.info("FIM do upload");

            return amazonS3.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("erro ao converter URL");
        }
    }
}
