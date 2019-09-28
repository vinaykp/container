package com.bottlerocketservices.rocketstream.container.service;

import com.bottlerocketservices.rocketstream.container.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContainerService {
    @Autowired
    private ExternalService externalService;

    public ContainerService(ExternalService externalService){
        this.externalService = externalService;
    }
    public List<Container> getAllContainer(){
        Set<Integer> containerIds = getUniqueContainerIds();

        List<Container> containerList = containerIds.parallelStream()
                .map(p -> getContainer(p))
                .filter(p -> p != null)
                .collect(Collectors.toList());

        return containerList;
    }

    public Container getContainer(int containerId){
        String title = "container-"+containerId;
        Container container = new Container();

        container.setId(containerId);
        List<Advertisement> ads = getContainerAdverstisements(containerId);
        if (ads.size() > 0) {
            title += "_ads";
            container.setAds(ads);
        }
        List<Image> images = getContainerImages(containerId);
        if(images.size() > 0 ){
            title += "_images";
            container.setImages(images);
        }
        List<Video> videos = getContainerVideos(containerId);
        if (videos.size()>0) {
            title += "_videos";
            container.setVideos(videos);
            container.setTitle(title);
        }else {
            log.info("Rocketstream Container Service found no videos for container id {}." , containerId);
            container = null;
        }

        return container;
    }
    public  List<Image> getContainerImages(int containerId) {
        List<Image> imagesList = new ArrayList<>();
        imagesList = externalService.imagesService(containerId);
        return imagesList;
    }

    public List<Advertisement> getContainerAdverstisements(int containerId){
        List<Advertisement> advertisementList = new ArrayList<>();
        advertisementList = externalService.adsService(containerId);
        return advertisementList;
    }

    public List<Video> getContainerVideos(int containerId){
        List<Video> videoList = new ArrayList<>();
        videoList = externalService.videosService(containerId);
        return videoList;
    }

    private Set<Integer> getUniqueContainerIds(){
        List<Video> videos = externalService.videosService();;
        Set<Integer> conatinerSet = videos.stream()
                .map(Video::getContainerId)
                .collect(Collectors.toSet());
        return conatinerSet;
    }

}
