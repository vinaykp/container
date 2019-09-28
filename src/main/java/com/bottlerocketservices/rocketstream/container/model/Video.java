package com.bottlerocketservices.rocketstream.container.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Video {
    int id;
    String title;
    String description;
    String playbackUrl;
    VideoType type;
    int containerId;
    String expirationDate;
    List<VideoAsset> assets;

}
