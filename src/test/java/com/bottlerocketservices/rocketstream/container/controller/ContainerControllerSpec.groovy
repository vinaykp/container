package com.bottlerocketservices.rocketstream.container.controller

import com.bottlerocketservices.rocketstream.container.ContainerApplication
import com.bottlerocketservices.rocketstream.container.service.ContainerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ContextConfiguration(classes = ContainerApplication.class)
@AutoConfigureMockMvc(secure = false)
@WebMvcTest(ContainerController)
class ContainerControllerSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @MockBean
    ContainerService containerService

    @Value('${baseUrl}')
    String baseUrl;

    def "Hello World Endpoint"() {
        when: "Status is 200 and the response is 'Hello world!'"
        String res = mockMvc.perform(get(baseUrl+ "/hello"))
                .andReturn()
                .response
                .contentAsString

        then:
                res == "hello world"
    }

    def "GetContainerImages Endpoint"() {
        given:
        containerService.getContainerImages(1)  >> []

        expect:"status code as 200 and response "
        mockMvc.perform(get(baseUrl+"/1/images"))
        .andExpect(status().isOk())
        .andReturn()
    }

    def "GetContainerAdvertisements"() {
        given:
        containerService.getContainerAdverstisements(1)  >> []

        expect:"status code as 200 and response "
        mockMvc.perform(get(baseUrl+"/1/ads"))
                .andExpect(status().isOk())
                .andReturn()
    }

    def "GetContainerVideos"() {
        given:
        containerService.getContainerVideos(1)  >> []

        expect:"status code as 200 and response "
        mockMvc.perform(get(baseUrl+"/1/videos"))
                .andExpect(status().isOk())
                .andReturn()
    }

    def "GetContainer"() {
        given:
        containerService.getContainer(1)  >> {}

        expect:"status code as 200 and response "
        mockMvc.perform(get(baseUrl+"/1"))
                .andExpect(status().isOk())
                .andReturn()
    }

    def "GetAllContainer"() {
        given:
        containerService.getAllContainer()  >> []

        expect:"status code as 200 and response "
        mockMvc.perform(get(baseUrl+"/"))
                .andExpect(status().isOk())
                .andReturn()
    }
}
