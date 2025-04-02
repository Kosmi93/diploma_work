package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.model.Ad;

import java.util.List;

public interface AdsService {
    Ad save(Ad ad);
    AdsDto getAll();
    Ad getById(Long id);
    void deleteById(Long id);
    List<Ad> getMeAds(Long id);
}
