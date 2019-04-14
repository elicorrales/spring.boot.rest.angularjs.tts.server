package com.spring.boot.rest.angularjs.ttsserver.controller;

import com.spring.boot.rest.angularjs.ttsserver.dto.Customer;
import com.spring.boot.rest.angularjs.ttsserver.dto.MyResponse;
import com.spring.boot.rest.angularjs.ttsserver.dto.Text2Convert;
import com.spring.boot.rest.angularjs.ttsserver.service.Text2SpeechService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public MyResponse text2speech(@RequestBody Text2Convert text2convert) {
        System.err.println("\n\n"+text2convert+"\n\n");
        service.convertText2Speech(text2convert.getTextToConvert());
        return null;
    }

    @PostMapping("customer")
    public MyResponse text2speech(@RequestBody Customer customer) {
        System.err.println("\n\n"+customer+"\n\n");
        return null;
    }

}