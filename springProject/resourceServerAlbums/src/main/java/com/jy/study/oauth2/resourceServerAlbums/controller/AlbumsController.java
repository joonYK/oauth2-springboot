package com.jy.study.oauth2.resourceServerAlbums.controller;

import com.jy.study.oauth2.resourceServerAlbums.model.Album;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {
    
    @GetMapping
    public List<Album> getAlbums() {
        Album album1 = Album.builder()
                .id("albumId1")
                .userId("1")
                .albumTitle("Album 1 title")
                .albumDescription("Album 1 description")
                .albumUrl("Album 1 URL")
                .build();

        Album album2 = Album.builder()
                .id("albumId2")
                .userId("2")
                .albumTitle("Album 2 title")
                .albumDescription("Album 2 description")
                .albumUrl("Album 2 URL")
                .build();

        return Arrays.asList(album1, album2);
    }
}
