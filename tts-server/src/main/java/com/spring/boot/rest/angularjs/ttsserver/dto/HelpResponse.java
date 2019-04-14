package com.spring.boot.rest.angularjs.ttsserver.dto;

public class HelpResponse {
    private final String help;
    public HelpResponse(String help ) { this.help = help; }
    public String getHelp() { return help; }
}