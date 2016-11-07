package com.qaiware.task.bojinov.webapp.handler.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.MessageTypesEnum;
import com.qaiware.task.bojinov.model.factory.impl.EmotionMessageFactory;
import com.qaiware.task.bojinov.storage.IMessageStoreService;
import com.qaiware.task.bojinov.storage.exceptions.StorageFailureException;

public class EmotionRequestHandlerTest {
	private EmotionRequestHandler handler;

	@Before
	public void setup() {
		handler = Mockito.mock(EmotionRequestHandler.class);
		Mockito.when(handler.handle(Mockito.anyString())).thenCallRealMethod();
		Mockito.when(handler.getMessageFactory()).thenReturn(new EmotionMessageFactory());
		Mockito.when(handler.getStorageServiceBeanName()).thenReturn("mockStorageService");
	}

	@Test
	public void handleTest() {

		Mockito.when(handler.getStoreService()).thenCallRealMethod();
		handler.getStoreService().clear();

		ResponseEntity<String> response = handler.handle("emotion");
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		Assert.assertEquals(new Integer(1),
				new Integer(handler.getStoreService().getMessages(MessageTypesEnum.EMOTION).size()));
		Assert.assertEquals(new Integer(0),
				new Integer(handler.getStoreService().getMessages(MessageTypesEnum.TEXT).size()));

		ResponseEntity<String> response1 = handler.handle("eemotion");
		assertEquals(HttpStatus.CREATED, response1.getStatusCode());

		assertEquals(new Integer(2), new Integer(handler.getStoreService().getMessages(MessageTypesEnum.EMOTION).size()));
		assertEquals(new Integer(0),
				new Integer(handler.getStoreService().getMessages(MessageTypesEnum.TEXT).size()));
		
		ResponseEntity<String> invalidResponse = handler.handle("00");
		assertEquals(HttpStatus.PRECONDITION_FAILED, invalidResponse.getStatusCode());
		
		assertEquals(new Integer(2), new Integer(handler.getStoreService().getMessages(MessageTypesEnum.EMOTION).size()));
		assertEquals(new Integer(0),
				new Integer(handler.getStoreService().getMessages(MessageTypesEnum.TEXT).size()));

	}

	@Test
	public void handleStorageFailure() throws StorageFailureException{
		IMessageStoreService mockStoreService = Mockito.mock(IMessageStoreService.class);
		Mockito.doThrow(StorageFailureException.class).when(mockStoreService).store(Mockito.any(IMessage.class));
		
		Mockito.when(handler.getStoreService()).thenReturn(mockStoreService);
		
		ResponseEntity<String> response = handler.handle("text");
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

		assertEquals(new Integer(0), new Integer(handler.getStoreService().getMessages(MessageTypesEnum.EMOTION).size()));
		assertEquals(new Integer(0),
				new Integer(handler.getStoreService().getMessages(MessageTypesEnum.TEXT).size()));

	}
}
