package com.bottlerocketservices.rocketstream.container.service

import com.bottlerocketservices.rocketstream.container.model.Advertisement
import com.bottlerocketservices.rocketstream.container.model.Container
import org.spockframework.spring.SpringBean
import org.spockframework.spring.SpringSpy
import org.spockframework.spring.UnwrapAopProxy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean


class ContainerServiceSpec extends spock.lang.Specification {

    @MockBean
    private ExternalService externalService = Mock();

    @Autowired
    ContainerService containerService = new ContainerService(externalService);

    def "GetAllContainer"() {
        when:
        def videosList =[]
        externalService.adsService(_) >> [1,2]
        externalService.imagesService(_) >> [1,2]
        externalService.videosService(_) >> [1,2]
        externalService.videosService() >> videosList
        and:
        List< Advertisement> res = containerService.getAllContainer()

        then:
        res.size() == 0
    }

    def "GetContainer"() {
        when:
        externalService.adsService(_) >> [1,2]
        externalService.imagesService(_) >> [1,2]
        externalService.videosService(_) >> [1,2]
        and:
        Container res = containerService.getContainer(1)

        then:
        res != null
    }


    def "Get Container Images "() {
        when:
        externalService.imagesService(_) >> [1,2]
        and:
        List< Advertisement> res = containerService.getContainerImages(1)

        then:
        res.size() == 2
    }


    def "GetContainerAdverstisements with Data"() {
        when:
        externalService.adsService(_) >> [1,2]
        and:
        List< Advertisement> res = containerService.getContainerAdverstisements(1)

        then:
        res.size() == 2
    }

    def "GetContainerAdverstisements without Data"() {
        when:
        externalService.adsService(_) >> []
        and:
        List< Advertisement> res = containerService.getContainerAdverstisements(1)

        then:
        res.size() == 0
    }

    def "GetContainerVideos"() {
        when:
        externalService.videosService(_) >> [1,2]
        and:
        List< Advertisement> res = containerService.getContainerVideos(1)

        then:
        res.size() == 2
    }
}
