package com.spring.boot.rest.angularjs.ttsserver.service;

import java.util.Map;

import com.harium.hci.espeak.Espeak;
import com.harium.hci.espeak.OutputLine;
import com.harium.hci.espeak.Espeak.SpeakCommandExecutionType;
import com.harium.hci.espeak.Voice;

import org.springframework.stereotype.Service;

@Service
public class Text2SpeechService {

    public void convertTextToSpeech(String textToConvert) throws Exception {
        new Espeak().speak(SpeakCommandExecutionType.NOT_THREADED , textToConvert);
    }


    public String convertTextToSpeech(Map<String,String> params, boolean toFile) throws Exception {
        Espeak espeak;
        String text2convert = params.get("textToConvert");
        if (params.size()>1) {
            Voice voice = new Voice();
            if (params.get("language")!=null) { voice.setName(params.get("language")); }
            espeak = new Espeak(voice);
        } else {
            espeak = new Espeak();
        }

        if (toFile) {
            return espeak.speakToFile(SpeakCommandExecutionType.NOT_THREADED, text2convert);
        } else {
            return espeak.speak(SpeakCommandExecutionType.NOT_THREADED, text2convert);
        }
    }


    public String[] killSpeech() throws Exception {
        return new Espeak().killSpeech();
    }

    public OutputLine[] runCommand(String command) throws Exception {
        return new Espeak().runCommand(command);
    }

    public String getSpeakCommandHelp() throws Exception {
        return new Espeak().getSpeakCommandHelp();
    }

    public String[] getSpeakCommandVoices() throws Exception {
        return new Espeak().getSpeakCommandVoices();
    }

}