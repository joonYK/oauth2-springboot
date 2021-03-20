package com.jy.study.oauth2.resourceServerPhotos.controller;

import com.jy.study.oauth2.resourceServerPhotos.model.Photo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotosController {
    
    @GetMapping
    public List<Photo> getAlbums() {
        Photo photo1 = Photo.builder()
                .id("albumId1")
                .userId("1")
                .photoTitle("Photo 1 title")
                .photoDescription("Photo 1 description")
                .photoUrl("Photo 1 URL")
                .build();

        Photo photo2 = Photo.builder()
                .id("albumId1")
                .userId("2")
                .photoTitle("Photo 2 title")
                .photoDescription("Photo 2 description")
                .photoUrl("Photo 2 URL")
                .build();

        return Arrays.asList(photo1, photo2);
    }
}
