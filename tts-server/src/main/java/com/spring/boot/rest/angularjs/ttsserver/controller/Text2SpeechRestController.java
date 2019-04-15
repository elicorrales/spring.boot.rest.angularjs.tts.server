package com.spring.boot.rest.angularjs.ttsserver.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.harium.hci.espeak.OutputLine;
import com.spring.boot.rest.angularjs.ttsserver.dto.Command;
import com.spring.boot.rest.angularjs.ttsserver.dto.MyResponse;
import com.spring.boot.rest.angularjs.ttsserver.dto.TextToConvert;
import com.spring.boot.rest.angularjs.ttsserver.service.Text2SpeechService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Text2SpeechRestController {

    @Autowired
    private Text2SpeechService service;

    @GetMapping("test")
    public ResponseEntity<?> test() throws Exception {
        service.convertTextToSpeech("This is a test.");
        return new ResponseEntity<>("Tested",HttpStatus.OK);
    }

    @GetMapping("help")
    public ResponseEntity<?> help() throws Exception {
        return new ResponseEntity<>(service.getSpeakCommandHelp(),HttpStatus.OK);
    }

    @GetMapping("voices")
    public ResponseEntity<?> voices() throws Exception {
        return new ResponseEntity<>(service.getSpeakCommandVoices(),HttpStatus.OK);
    }

    @PostMapping("speech")
    public  ResponseEntity<?> text2speech(@RequestBody TextToConvert textToConvert,
                @RequestParam(required=false,name="language") String language,
                @RequestParam(required=false,name="toFile") String toFile
                ) {
        try {

            if (textToConvert.getTextToConvert() == null) {
                throw new IllegalArgumentException("Missing text to convert");
            }

            Map<String,String> params = new HashMap<>();
            params.put("textToConvert", textToConvert.getTextToConvert());
            if (language != null) { params.put("language",language); }
            if (toFile == null || toFile.isEmpty() || toFile.equals("undefined") || toFile.equals("false")) {
                String response = service.convertTextToSpeech(params,false);
                return new ResponseEntity<>(new MyResponse(response),HttpStatus.OK);
            } else {
                String response = service.convertTextToSpeech(params,true);
                return new ResponseEntity<>(new MyResponse(response),HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MyResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("speech")
    public  ResponseEntity<?> killSpeech() {
        try {
            String[] output = service.killSpeech();
            return new ResponseEntity<>(output,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("command")
    public  ResponseEntity<?> runCommand(@RequestBody Command command) {

        try {
            String commandStr = URLDecoder.decode(command.getCommand(),"UTF-8");
            OutputLine[] output = service.runCommand(commandStr.trim());
            return new ResponseEntity<>(output,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



}