package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdsService;

import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {
    private final AdRepository adRepository;

    public AdsServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public Ad save(Ad ad) {
        return adRepository.save(ad);
    }

    @Override
    public AdsDto getAll() {
        List<Ad> ads = adRepository.findAll();
        return new AdsDto(ads.size(), ads);
    }

    @Override
    public Ad getById(Long id) {
        return null;
       // return adRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        adRepository.deleteById(id);
    }

    @Override
    public List<Ad> getMeAds(Long id) {
        return adRepository.findByAuthor(new User(id));
    }
}
