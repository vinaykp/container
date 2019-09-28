package com.bottlerocketservices.rocketstream.container.service;

import com.bottlerocketservices.rocketstream.container.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExternalService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${adsUrl}")
    private String adsUrl;
    @Value("${imagesUrl}")
    private String imagesUrl;
    @Value("${videosUrl}")
    private String videosUrl;

    public List<Image> imagesService(int containerId){
        String url = imagesUrl + "?containerId="+containerId;
        Images imagesResponse = new Images();;
        try {
            imagesResponse = restTemplate.getForObject(url, Images.class);
        }catch (Exception e){
            log.info("Rocketstream Images Service Exception while accessing container {} : {}" , containerId, e.getMessage());
        }
        return Optional.ofNullable(imagesResponse.getImages()).orElseGet(ArrayList::new);
    }

    public List<Advertisement> adsService(int containerId){
        String url = adsUrl+ "?containerId="+containerId;
        Advertisements adsResponse = new Advertisements();
        try {
            adsResponse = restTemplate.getForObject(url, Advertisements.class);
        }catch (Exception e){
            log.info("Rocketstream Advertisement Service Exception while accessing container {} : {}" , containerId, e.getMessage());
        }
        return Optional.ofNullable(adsResponse.getAdvertisements()).orElseGet(ArrayList::new);
    }

    public List<Video> videosService(int containerId){
        String url = videosUrl + "?containerId="+containerId;
        Videos videosResponse = new Videos();
        try {
            videosResponse = restTemplate.getForObject(url, Videos.class);
        }catch (Exception e){
            log.info("Rocketstream Video Service Exception while accessing container {} : {}" , containerId, e.getMessage());
        }
        return Optional.ofNullable(videosResponse.getVideos()).orElseGet(ArrayList::new);
    }

    public List<Video> videosService(){
        String url = videosUrl;
        Videos videosResponse = new Videos();
        try {
            videosResponse = restTemplate.getForObject(url, Videos.class);
        }catch (Exception e){
            log.info("Rocketstream Video Service Exception : {}" , e.getMessage());
        }
        return Optional.ofNullable(videosResponse.getVideos()).orElseGet(ArrayList::new);
    }
}
