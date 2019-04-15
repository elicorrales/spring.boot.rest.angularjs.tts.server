package com.harium.hci.espeak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Reference: http://espeak.sourceforge.net/commands.html
 */
public class Espeak {

    public static enum SpeakCommandExecutionType { THREADED_NO_RESULT,  NOT_THREADED };
    public static final String COMMAND_ESPEAK = "espeak";
    public static final String COMMAND_ESPEAK_TO_FILE = "espeak -w ";
    public static final String COMMAND_KILL_ESPEAK =  "./tts-server/scripts/kill.sh";
    public static final String COMMAND_COMMAND =  "./tts-server/scripts/command.sh";
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
        proc.waitFor();
        proc.destroy();
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
        proc.waitFor();
        proc.destroy();
        return voices.toArray(new String[voices.size()]);
    }


    public String[] killSpeech() throws Exception {
        String command = COMMAND_KILL_ESPEAK;
        Process proc = Runtime.getRuntime().exec(command);
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        List<String> output = new ArrayList<>();
        String line;
        while ((line = in.readLine()) != null) {
            output.add("{line:"+line+"}");
        }
        proc.waitFor();
        proc.destroy();
        String[] response = output.toArray(new String[output.size()]);
        return response;
    }


    public OutputLine[] runCommand(String command) throws Exception {
        String commandStr = COMMAND_COMMAND + " '" + command + "'";
        Process proc = Runtime.getRuntime().exec(commandStr);
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        List<OutputLine> output = new ArrayList<>();
        String line;
        while ((line = in.readLine()) != null) {
            //output.add(line);
            output.add(new OutputLine(line));
        }
        proc.waitFor();
        proc.destroy();
        OutputLine[] response = output.toArray(new OutputLine[output.size()]);
        return response;
    }

    public void speak(SpeakCommandExecutionType execHow, String text) throws Exception {
        speak(execHow, COMMAND_ESPEAK, text);
    }

    public void speakToFile(SpeakCommandExecutionType execHow, String text) throws Exception {
        String fileName = new Date().getTime()+".wav";
        String command = COMMAND_ESPEAK_TO_FILE + fileName;
        speak(execHow, command , text);
    }

    public void speak(SpeakCommandExecutionType execHow, String command, String text) throws Exception {
        if (text == null || text.isEmpty()) { throw new IllegalArgumentException("Missing Text"); }
        executeSpeakCommand(execHow, command,
                "-v", voice.getName() + voice.getVariant(),
                "-p", Integer.toString(voice.getPitch()),
                "-a", Integer.toString(voice.getAmplitude()),
                "-s", Integer.toString(voice.getSpeed()),
                "-g", Integer.toString(voice.getGap()),
                text);
    }

    private static void executeSpeakCommand(final SpeakCommandExecutionType execHow, final String ... command)  throws Exception {
        switch (execHow) {
            case NOT_THREADED:
                        executeSpeakCommandNoThread(command);
                        break;
            case THREADED_NO_RESULT:
                        executeSpeakCommandSingleThreadNoWaitForResult(command);
                        break;
        }
    }

    private static void executeSpeakCommandNoThread(final String ... command) throws Exception {
        ProcessBuilder b = new ProcessBuilder(command);
        Process process = b.start();
        process.waitFor();
        process.destroy();
    }

    private static void executeSpeakCommandSingleThreadNoWaitForResult(final String ... command) {
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
