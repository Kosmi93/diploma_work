package ru.skypro.homework.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdsServiceImpl implements AdsService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    public AdsServiceImpl(AdRepository adRepository, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Ad save(AdDto adDto) {
        return AdMapper.toAd(adDto);

    }

    @Override
    public AdsDto getAll() {

        return AdMapper.toAds(adRepository.findAll());
    }

    @Override
    public Ad getById(Integer id) {

        return adRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Integer id) {
        adRepository.deleteById(id);
    }

    @Override
    public AdsDto getMeAds() {
        List<Ad> listAd = adRepository.findByAuthor(User.builder().id(getUserId()).build());
        return AdMapper.toAds(listAd);
    }

    @Override
    public ExtendedAdDto geInfo(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow();
        return AdMapper.toExtendedAdDto(ad);
    }

    @Override
    public CreateOrUpdateAdDto update(Integer id, CreateOrUpdateAdDto ad) {
        return null;
    }

    private Integer getUserId(){
        var userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return userRepository.findByUserName(userDetails.getUsername()).orElseThrow().getId();

    }
}
