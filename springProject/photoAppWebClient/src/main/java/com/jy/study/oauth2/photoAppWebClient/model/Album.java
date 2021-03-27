package com.jy.study.oauth2.photoAppWebClient.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Album {

    private String id;

    private String userId;

    private String albumTitle;

    private String albumDescription;

    private String albumUrl;
}
