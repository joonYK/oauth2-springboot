package com.jy.study.oauth2.resourceServerPhotos.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Photo {

    private String id;

    private String albumId;

    private String userId;

    private String photoTitle;

    private String photoDescription;

    private String photoUrl;
}
