package com.spring.boot.rest.angularjs.ttsserver.controller;

import com.spring.boot.rest.angularjs.ttsserver.dto.MyResponse;
import com.spring.boot.rest.angularjs.ttsserver.service.Text2SpeechService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Text2SpeechRestController {

    @Autowired
    private Text2SpeechService service;

    @GetMapping("test")
    public MyResponse test() {
        service.convertText2Speech("This is a test.");
        return null;
    }

    @PostMapping("text2speech")
    public MyResponse text2speech() {

        service.convertText2Speech("This is a test.");
        return null;
    }

}