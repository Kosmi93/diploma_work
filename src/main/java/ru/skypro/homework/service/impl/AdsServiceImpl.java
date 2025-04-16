package ru.skypro.homework.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final ImgServiceImpl imgService;

    public AdsServiceImpl(AdRepository adRepository, UserRepository userRepository, ImgServiceImpl imgService) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.imgService = imgService;
    }


    @Override
    public Ad save(CreateOrUpdateAdDto adsDto, MultipartFile img) {
        int userId = getUserId();
        Ad ad = adRepository.save(AdMapper.toAd(adsDto,userId));
        imgService.uploadImg(ad.getPk(),img);
        return ad;
    }

    @Override
    public AdsDto getAll() {
        return AdMapper.toAds(adRepository.findAll());
    }

    @Override
    public Ad getById(Integer id) {

        return adRepository.findById(id).orElseThrow(() -> new AdNotFoundException("Ad is not found"));
    }

    @Override
    public void deleteById(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new AdNotFoundException("Ad is not found"));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if(ad.getAuthor().getId().equals(user.getId())) {
            adRepository.deleteById(id);
        } else {
            throw new RuntimeException("The user does not have access");
        }

    }

    @Override
    public AdsDto getMeAds() {
        List<Ad> listAd = adRepository.findByAuthor(User.builder().id(getUserId()).build());
        return AdMapper.toAds(listAd);
    }

    @Override
    public ExtendedAdDto geInfo(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new AdNotFoundException("Ad is not found"));
        return AdMapper.toExtendedAdDto(ad);
    }

    @Override
    public CreateOrUpdateAdDto update(Integer id, CreateOrUpdateAdDto adInfo) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new AdNotFoundException("Ad is not found"));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        if(ad.getAuthor().getId().equals(user.getId())) {
            ad.setTitle(adInfo.getTitle());
            ad.setDescription(adInfo.getDescription());
            ad.setPrice(adInfo.getPrice());
            adRepository.save(ad);
        } else {
            throw new RuntimeException("The user does not have access");
        }
        return null;
    }

    private Integer getUserId(){
        var userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return userRepository.findByUserName(userDetails.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found")).getId();

    }
}
