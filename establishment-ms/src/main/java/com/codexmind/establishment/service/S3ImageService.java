package com.codexmind.establishment.service;

import com.codexmind.establishment.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class S3ImageService implements  IMageServiceInterface{
    @Override
    public BufferedImage getJpgImageFromFile(MultipartFile multipartFile)  {
        String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (!".png".equals(ext) && !"jpg".equals(ext) && !"jpeg".equals(ext)) {
            throw  new FileException("Somente imagens PNG e JPG sao permitidas");
        }
        try {
            BufferedImage img = ImageIO.read(multipartFile.getInputStream());
            if("jpeg".equals(ext)){
                img = pngToJpg(img);
            }
            return img;
        } catch (IOException e) {
            throw new FileException("ERRO ao ler o arquivo!");
        }
    }
    @Override
    public BufferedImage pngToJpg(BufferedImage img) {

        BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);

        return jpgImage;
    }
    @Override
    public InputStream getInputStream(BufferedImage img, String ext){
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, ext, os);
            return new ByteArrayInputStream(os.toByteArray());
        }catch (IOException e){
            throw new FileException("ERRO ao ler o arquivo!");
        }
    }

}
