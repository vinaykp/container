package com.bottlerocketservices.rocketstream.container.controller;

import com.bottlerocketservices.rocketstream.container.model.*;
import com.bottlerocketservices.rocketstream.container.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("containers")
public class ContainerController {

    @Autowired
    ContainerService containerService;

    @GetMapping("/hello")
    public String getHello(){
        return "hello world";
    }

    @GetMapping("/{containerId}/images")
    public List<Image> getContainerImages(@PathVariable("containerId") int containerId){
        return containerService.getContainerImages(containerId);
    }

    @GetMapping("/{containerId}/ads")
    public List<Advertisement> getContainerAdvertisements(@PathVariable("containerId") int containerId){
        return containerService.getContainerAdverstisements(containerId);
    }

    @GetMapping("/{containerId}/videos")
    public List<Video> getContainerVideos(@PathVariable("containerId") int containerId){
        return containerService.getContainerVideos(containerId);
    }

    @GetMapping("/{containerId}")
    public Container getContainer(@PathVariable("containerId") int containerId){
        return containerService.getContainer(containerId);
    }

    @GetMapping("")
    public List<Container> getAllContainer(){
        return containerService.getAllContainer();
    }
}
