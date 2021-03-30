package com.jy.study.oauth2.photoAppWebClient.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    private String id;

    private String userId;

    private String albumTitle;

    private String albumDescription;

    private String albumUrl;
}
