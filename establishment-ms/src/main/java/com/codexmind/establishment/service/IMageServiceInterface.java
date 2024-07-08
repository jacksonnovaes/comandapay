package com.codexmind.establishment.service;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public interface IMageServiceInterface {

    BufferedImage getJpgImageFromFile(MultipartFile multipartFile);

    BufferedImage pngToJpg(BufferedImage img);

    InputStream getInputStream(BufferedImage img, String ext);
}
