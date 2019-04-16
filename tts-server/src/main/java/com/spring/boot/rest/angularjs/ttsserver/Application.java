package com.spring.boot.rest.angularjs.ttsserver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
		static final String waveFilePath = "tts-server/src/main/resources/static/wave.files";

	public static void main(String[] args) throws Exception {
		if (Paths.get(waveFilePath).toFile().exists()) {
			for (File f : Paths.get(waveFilePath).toFile().listFiles()) {
				if (f.isFile()) {
					f.delete();
				}
			}
		}

		SpringApplication.run(Application.class, args);
	}

}
