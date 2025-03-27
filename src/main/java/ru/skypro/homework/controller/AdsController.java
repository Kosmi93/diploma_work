package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.model.ExtendedAd;
import ru.skypro.homework.service.impl.ImgServiceImpl;

@Slf4j
//@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/asd")
@Tag(name = "Объявления")
public class AdsController {
    private final ImgServiceImpl imgService;


    @GetMapping()
    @Operation(summary = "Получение списка всех объявлений")
    public ResponseEntity<?> getAll() {
        return null;
    }


    @PostMapping()
    @Operation(summary = "Создание объявления")
    public ResponseEntity<?> add(@RequestBody Ad ad) {
        return null;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ResponseEntity<ExtendedAd> getInfo(@PathVariable("id") Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public void delete(@PathVariable("id") Long id) {

    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление объявления")
    public ResponseEntity<ExtendedAd> update(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    public ResponseEntity<?> getAllMe() {
        return null;
    }

    @PatchMapping(value ="/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления")
    public String updateImages(@PathVariable("id") Long id,@RequestParam MultipartFile img) {
        return imgService.uploadImg(id,img);

    }

}
