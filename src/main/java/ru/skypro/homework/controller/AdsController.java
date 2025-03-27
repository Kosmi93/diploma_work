package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.model.AdsInfo;
import ru.skypro.homework.service.impl.ImgServiceImpl;

@Slf4j
//@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/asd")
public class AdsController {
    private final ImgServiceImpl imgService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return null;
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody Ads ads) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdsInfo> getInfo(@PathVariable("id") Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return null;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdsInfo> update(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getAllMe() {
        return null;
    }

    @PatchMapping(value ="/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateImages(@PathVariable("id") Long id,@RequestParam MultipartFile img) {
        return imgService.uploadImg(id,img);

    }

}
