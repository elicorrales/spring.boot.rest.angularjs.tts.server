package com.spring.boot.rest.angularjs.ttsserver.controller;

import java.util.HashMap;
import java.util.Map;

import com.spring.boot.rest.angularjs.ttsserver.dto.Customer;
import com.spring.boot.rest.angularjs.ttsserver.dto.HelpResponse;
import com.spring.boot.rest.angularjs.ttsserver.dto.MyResponse;
import com.spring.boot.rest.angularjs.ttsserver.dto.TextToConvert;
import com.spring.boot.rest.angularjs.ttsserver.service.Text2SpeechService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Text2SpeechRestController {

    @Autowired
    private Text2SpeechService service;

    @GetMapping("test")
    public MyResponse test() {
        service.convertTextToSpeech("This is a test.");
        return null;
    }

    @GetMapping("help")
    public HelpResponse help() throws Exception {
        return new HelpResponse(service.getSpeakCommandHelp());
    }

    @GetMapping("voices")
    public String[] voices() throws Exception {
        return service.getSpeakCommandVoices();
    }

    @PostMapping("text2speech")
    public MyResponse text2speech(@RequestBody TextToConvert textToConvert,
                @RequestParam(required=false,name="language") String language
                ) {
        Map<String,String> params = new HashMap<>();
        params.put("textToConvert", textToConvert.getTextToConvert());
        if (language != null) { params.put("language",language); }
        service.convertTextToSpeech(params);
        return null;
    }

/*
    @PostMapping("text2speech")
    public MyResponse text2speech(@RequestBody TextToConvert textToConvert) {
        service.convertTextToSpeech(textToConvert.getTextToConvert());
        return null;
    }
*/
    @PostMapping("customer")
    public MyResponse text2speech(@RequestBody Customer customer) {
        System.err.println("\n\n"+customer+"\n\n");
        return null;
    }

}