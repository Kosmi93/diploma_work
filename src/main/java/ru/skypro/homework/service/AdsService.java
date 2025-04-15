package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;

import java.util.List;

public interface AdsService {
    Ad save(AdDto adsDto);
    AdsDto getAll();
    Ad getById(Integer id);
    void deleteById(Integer id);
    AdsDto getMeAds();

    ExtendedAdDto geInfo(Integer id);

    CreateOrUpdateAdDto update(Integer id, CreateOrUpdateAdDto ad);
}
