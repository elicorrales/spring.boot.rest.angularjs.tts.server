package com.spring.boot.rest.angularjs.ttsserver;

import com.harium.hci.espeak.Espeak;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testEspeak() throws Exception {
		Espeak espeak = new Espeak();
		espeak.speak(Espeak.SpeakCommandExecutionType.NOT_THREADED, "This is a test");
	}

	@Test
	public void testEspeak2() throws Exception {
		Espeak espeak = new Espeak();
		espeak.speak(Espeak.SpeakCommandExecutionType.THREADED_NO_RESULT, "This is a test");
	}


}
