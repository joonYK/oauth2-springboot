package com.jy.study.oauth2.photoAppWebClient.controller;

import com.jy.study.oauth2.photoAppWebClient.model.Album;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AlbumsController {

    private final OAuth2AuthorizedClientService oAuth2ClientService;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient oauth2Client = oAuth2ClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName());

        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);

        System.out.println("Principal = " + principal);

        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);


        String url = "http://localhost:8082/albums";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);

        HttpEntity<List<Album>> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Album>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Album>>() {});
        List<Album> albums = responseEntity.getBody();

        model.addAttribute("albums", albums);

        /*
        Album album1 = Album.builder()

                .id("albumOne")
                .albumTitle("Album one title")
                .albumUrl("http://localhost:8082/albums/1")
                .build();

        Album album2 = Album.builder()
                .id("albumTwo")
                .albumTitle("Album two title")
                .albumUrl("http://localhost:8082/albums/2")
                .build();

        model.addAttribute("albums", Arrays.asList(album1, album2));
        */


        return "albums";
    }
}
