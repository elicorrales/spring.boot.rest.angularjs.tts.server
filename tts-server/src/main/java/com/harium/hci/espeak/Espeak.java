package com.harium.hci.espeak;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Reference: http://espeak.sourceforge.net/commands.html
 */
public class Espeak {

    public static enum ExecutionType { THREADED_NO_RESULT, THREADED_WAIT_FOR_RESULT, NOT_THREADED };
    public static final String COMMAND_ESPEAK = "espeak";
    private Voice voice;

    public Espeak() {
        this(new Voice());
    }

    public Espeak(Voice voice) {
        this.voice = voice;
    }

    public String getSpeakCommandHelp() throws Exception {
        String command = COMMAND_ESPEAK + " --help";
        Process proc = Runtime.getRuntime().exec(command);
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    public String[] getSpeakCommandVoices() throws Exception {
        String command = COMMAND_ESPEAK + " --voices";
        Process proc = Runtime.getRuntime().exec(command);
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        List<String> voices = new ArrayList<>();
        String line;
        while ((line = in.readLine()) != null) {
            voices.add(line);
        }
        return voices.toArray(new String[voices.size()]);
    }



    public void speak(ExecutionType execHow, String text) throws Exception {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Missing Text");
        }
        execute(execHow, COMMAND_ESPEAK,
                "-v", voice.getName() + voice.getVariant(),
                "-p", Integer.toString(voice.getPitch()),
                "-a", Integer.toString(voice.getAmplitude()),
                "-s", Integer.toString(voice.getSpeed()),
                "-g", Integer.toString(voice.getGap()),
                text);
    }

    private static void execute(final ExecutionType execHow, final String ... command)  throws Exception {
        switch (execHow) {
            case NOT_THREADED:
                        executeNoThread(command);
                        break;
            case THREADED_NO_RESULT:
                        executeSingleThreadNoWaitForResult(command);
                        break;
        }
    }

    private static void executeNoThread(final String ... command) throws Exception {
        ProcessBuilder b = new ProcessBuilder(command);
        Process process = b.start();
        process.waitFor();
        process.destroy();
    }

    private static void executeSingleThreadNoWaitForResult(final String ... command) {
        String threadName = "espeak";
        new Thread(new Runnable() {
            public void run() {
                ProcessBuilder b = new ProcessBuilder(command);
                try {
                    Process process = b.start();
                    process.waitFor();
                    process.destroy();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, threadName).start();
    }

}
