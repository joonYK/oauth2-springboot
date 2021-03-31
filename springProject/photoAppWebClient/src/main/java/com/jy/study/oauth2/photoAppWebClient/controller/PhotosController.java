package com.jy.study.oauth2.photoAppWebClient.controller;

import com.jy.study.oauth2.photoAppWebClient.model.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PhotosController {

    private final WebClient webClient;

    @GetMapping("/photos")
    public String getPhotos(Model model) {

        String url = "http://localhost:8082/photos";
        List<Photo> photos = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Photo>>() {})
                .block();

        model.addAttribute("photos", photos);

        return "photos";
    }
}
