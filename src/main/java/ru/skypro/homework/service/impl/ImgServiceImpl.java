package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImgService;

import java.io.IOException;

@Service
public class ImgServiceImpl implements ImgService {



    @Override
    public String uploadImg(Integer id, MultipartFile imgFile, String path) {
        String fileName ="";
        try {
            fileName = save(id, imgFile, path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }
}
