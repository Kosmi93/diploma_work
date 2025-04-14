package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImgService;

import java.io.IOException;

@Service
public class ImgServiceImpl implements ImgService {

    @Value("${path.to.img.folder}")
    private  String path;


    @Override
    public String uploadImg(Integer id, MultipartFile imgFile) {
        String fileName ="";
        try {
            fileName = save(id, imgFile, path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }
}
