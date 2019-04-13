package com.spring.boot.rest.angularjs.ttsserver.service;

import com.harium.hci.espeak.Espeak;

import org.springframework.stereotype.Service;

@Service
public class Text2SpeechService {

    private final Espeak espeak = new Espeak();

    public void convertText2Speech(String text) {

        espeak.speak(text);
    }
}