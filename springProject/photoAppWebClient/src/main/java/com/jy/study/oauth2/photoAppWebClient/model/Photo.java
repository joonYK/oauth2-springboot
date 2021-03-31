package com.jy.study.oauth2.photoAppWebClient.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    private String id;

    private String albumId;

    private String userId;

    private String photoTitle;

    private String photoDescription;

    private String photoUrl;
}
