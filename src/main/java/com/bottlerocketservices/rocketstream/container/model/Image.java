package com.bottlerocketservices.rocketstream.container.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Image {
    int id;
    String name;
    String url;
    int containerId;
}
