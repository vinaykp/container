package com.bottlerocketservices.rocketstream.container.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Container {
    int id;
    String title;
    List<Advertisement> ads;
    List<Image> images;
    List<Video> videos;
}
