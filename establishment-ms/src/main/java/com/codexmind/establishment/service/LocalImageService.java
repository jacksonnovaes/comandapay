package com.codexmind.establishment.service;

import com.codexmind.establishment.exceptions.FileException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class LocalImageService implements IMageServiceInterface {

    private final String uploadDir = "./uploads";  // Diretório relativo para salvar imagens
    private final String serverBaseUrl = "http://192.168.0.129:8080";

    @Override
    public BufferedImage getJpgImageFromFile(MultipartFile multipartFile) {
        String ext = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.') + 1).toLowerCase();
        if (!"png".equals(ext) && !"jpg".equals(ext) && !"jpeg".equals(ext)) {
            throw new FileException("Somente imagens PNG e JPG são permitidas");
        }
        try {
            BufferedImage img = ImageIO.read(multipartFile.getInputStream());
            if ("png".equals(ext)) {
                img = pngToJpg(img);
            }
            return img;
        } catch (IOException e) {
            throw new FileException("ERRO ao ler o arquivo!");
        }
    }

    @Override
    public BufferedImage pngToJpg(BufferedImage img) {
        BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
        return jpgImage;
    }

    @Override
    public InputStream getInputStream(BufferedImage img, String ext) {
        return null;
    }

    public URI saveImage(MultipartFile multipartFile) {
        try {
            BufferedImage img = getJpgImageFromFile(multipartFile);
            String fileName = multipartFile.getOriginalFilename();
            assert fileName != null;
            File outputFile = new File(uploadDir, fileName);

            // Certifique-se de que o diretório de upload existe
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            ImageIO.write(img, "jpg", outputFile);
            // Construir o URI da imagem salva
            String fileUri = serverBaseUrl + "/uploads/" + fileName;
            return new URI(fileUri.replace(" ", "%20"));
        } catch (IOException e) {
            throw new FileException("Erro ao salvar a imagem!");
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao construir o URI da imagem!");
        }
    }
}
