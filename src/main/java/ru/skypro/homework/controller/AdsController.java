package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.impl.ImgServiceImpl;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {
    private final AdsService service;
   // private final ImgServiceImpl imgService;


    @GetMapping()
    @Operation(summary = "Получение списка всех объявлений")
    public ResponseEntity<AdsDto> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }


    @PostMapping()
    @Operation(summary = "Создание объявления")
    public ResponseEntity<?> add(@RequestBody AdDto ad) {
        service.save(ad);
        return new ResponseEntity<>(service.save(ad),HttpStatus.valueOf(201));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ResponseEntity<ExtendedAdDto> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.geInfo(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public void delete(@PathVariable("id") Long id) {

    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление объявления")
    public ResponseEntity<CreateOrUpdateAdDto> update(@PathVariable("id") Long id,@RequestBody CreateOrUpdateAdDto ad) {
        return ResponseEntity.ok(service.update(id,ad));
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    public ResponseEntity<?> getAllMe() {
        service.getMeAds();
        return null;
    }

    @PatchMapping(value ="/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления")
    public String updateImages(@PathVariable("id") Long id,@RequestParam MultipartFile img) {
        return /*imgService.uploadImg(id,img)*/null;

    }

}
