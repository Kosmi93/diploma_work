package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class AdMapper {
    public static Ad toAd(AdDto adDto){
        User user = new User();
        user.setId(adDto.getAuthor());
        return Ad.builder()
                .pk(adDto.getPk())
                .author(user)
                .image(adDto.getImage())
                .price(adDto.getPrice())
                .title(adDto.getTitle())
                .description(adDto.getDescription())
                .build();
    }

    public static AdsDto toAds(List<Ad>  adDtos){
      return AdsDto.builder()
              .count(adDtos.size())
              .result(adDtos.stream()
                      .map(s->  AdDto.builder()
                              .author(s.getAuthor().getId())
                              .title(s.getTitle())
                              .image(s.getImage())
                              .price(s.getPrice())
                              .pk(s.getPk())
                              .description(s.getDescription())
                              .build())
                      .collect(Collectors.toList()))
              .build();

    }

    public static ExtendedAdDto toExtendedAdDto(Ad ad){
        return ExtendedAdDto.builder()
                .pk(ad.getPk())
                .authorFirstName(ad.getAuthor().getFirstName())
                .authorLastName(ad.getAuthor().getLastName())
                .description(ad.getDescription())
                .email(ad.getAuthor().getUserName())
                .image(ad.getImage())
                .phone(ad.getAuthor().getPhone())
                .price(ad.getPrice())
                .title(ad.getTitle())
                .build();

    }
}
