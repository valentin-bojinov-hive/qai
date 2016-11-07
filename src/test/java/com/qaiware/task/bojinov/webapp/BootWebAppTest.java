package com.qaiware.task.bojinov.webapp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qaiware.task.bojinov.webapp.handler.impl.EmotionRequestHandler;
import com.qaiware.task.bojinov.webapp.handler.impl.TextRequestHandler;

public class BootWebAppTest {
	private BootWebApp app;
	private TextRequestHandler textHandler = new TextRequestHandler() {
		@Override
		protected String getStorageServiceBeanName() {
			return "mockStorageService";
		}
	};

	private EmotionRequestHandler emotionRequestHandler = new EmotionRequestHandler() {
		@Override
		protected String getStorageServiceBeanName() {
			return "mockStorageService";
		}
	};

	@Before
	public void setup() {
		app = Mockito.mock(BootWebApp.class);
		Mockito.when(app.newTextRequestHandler()).thenReturn(textHandler);
		Mockito.when(app.newEmotionRequestHandler()).thenReturn(emotionRequestHandler);
		
		Mockito.when(app.postEmotion(Mockito.anyString())).thenCallRealMethod();
		Mockito.when(app.postText(Mockito.anyString())).thenCallRealMethod();

	}

	@Test
	public void postEmotionTest() {
		ResponseEntity<String> response = app.postEmotion("emotion");
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}

	@Test
	public void postInvalidEmotionTest() {
		ResponseEntity<String> response = app.postEmotion("A0");
		assertEquals(HttpStatus.PRECONDITION_FAILED, response.getStatusCode());
		
		ResponseEntity<String> response1 = app.postEmotion("0");
		assertEquals(HttpStatus.PRECONDITION_FAILED, response1.getStatusCode());


	}

	@Test
	public void postTextTest() {
		ResponseEntity<String> response = app.postText("Text");
		assertEquals(HttpStatus.CREATED, response.getStatusCode());


	}

	@Test
	public void postInvalidTextTest() {
		ResponseEntity<String> response = app.postText("");
		assertEquals(HttpStatus.PRECONDITION_FAILED, response.getStatusCode());


	}
}
