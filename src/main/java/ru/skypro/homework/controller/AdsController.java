package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.impl.ImgServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {

    private final AdsService service;



    @GetMapping()
    @Operation(summary = "Получение списка всех объявлений")
    public ResponseEntity<AdsDto> getAll() {
        return ResponseEntity.ok(service.getAll());

    }


    @PostMapping()
    @Operation(summary = "Создание объявления")
    public ResponseEntity add(@RequestParam("image") MultipartFile img,@RequestPart("properties") CreateOrUpdateAdDto properties) {

        service.save(properties,img);
        return  ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ResponseEntity<ExtendedAdDto> getInfo(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(service.geInfo(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.status(200).build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(403).build();
        }catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление объявления")
    public ResponseEntity<CreateOrUpdateAdDto> update(@PathVariable("id") Integer id,@RequestBody CreateOrUpdateAdDto ad) {

        return ResponseEntity.ok(service.update(id,ad));
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    public ResponseEntity<?> getAllMe() {
        try {
            return ResponseEntity.ok(service.getMeAds());
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }

    }

    @PatchMapping(value ="/{id}/image")
    @Operation(summary = "Обновление картинки объявления")
    public String updateImages(@PathVariable("id") Integer id,@RequestParam("image") MultipartFile img) {

        return service.imageUpdate(id,img);
    }


}
