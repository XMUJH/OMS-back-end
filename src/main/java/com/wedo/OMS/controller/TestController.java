package com.wedo.OMS.controller;

import com.arcsoft.service.AFRService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@RestController
@ComponentScan(value="com.arcsoft")
public class TestController {
    private AFRService afrService;

    public TestController(AFRService afrService) {
        this.afrService = afrService;
    }

    @GetMapping(value = "/facetest")
    public String faceTest() {
        return afrService.doFR("src/main/resources/static/004.png", new String[]{"src/main/resources/static/faceimg/001.jpg","src/main/resources/static/faceimg/002.jpg","src/main/resources/static/faceimg/003.jpg"});
    }
}
