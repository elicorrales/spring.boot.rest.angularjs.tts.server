package com.spring.boot.rest.angularjs.ttsserver.service;

import java.util.List;
import java.util.Map;

import com.harium.hci.espeak.Espeak;
import com.harium.hci.espeak.Voice;

import org.springframework.stereotype.Service;

@Service
public class Text2SpeechService {

    public void convertTextToSpeech(String textToConvert) {
        new Espeak().speak(textToConvert);
    }


    public void convertTextToSpeech(Map<String,String> params) {
        Espeak espeak;
        String text2convert = params.get("textToConvert");
        if (params.size()>1) {
            Voice voice = new Voice();
            if (params.get("language")!=null) { voice.setName(params.get("language")); }
            espeak = new Espeak(voice);
        } else {
            espeak = new Espeak();
        }
        espeak.speak(text2convert);
    }

    public String getSpeakCommandHelp() throws Exception {
        return new Espeak().getSpeakCommandHelp();
    }

    public String[] getSpeakCommandVoices() throws Exception {
        return new Espeak().getSpeakCommandVoices();
    }

}