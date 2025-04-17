package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;

public interface AdsService {
    Ad save(CreateOrUpdateAdDto adsDto, MultipartFile img);
    AdsDto getAll();
    Ad getById(Integer id);
    void deleteById(Integer id);
    AdsDto getMeAds();
    String imageUpdate(int id,MultipartFile img);
    ExtendedAdDto geInfo(Integer id);

    CreateOrUpdateAdDto update(Integer id, CreateOrUpdateAdDto ad);
}
