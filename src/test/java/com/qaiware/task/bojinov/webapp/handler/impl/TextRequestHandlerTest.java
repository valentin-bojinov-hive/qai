package com.qaiware.task.bojinov.webapp.handler.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Assert;

import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.MessageTypesEnum;
import com.qaiware.task.bojinov.model.factory.impl.TextMessageFactory;
import com.qaiware.task.bojinov.storage.IMessageStoreService;
import com.qaiware.task.bojinov.storage.exceptions.StorageFailureException;

public class TextRequestHandlerTest {

	private TextRequestHandler handler;

	@Before
	public void setup() {
		handler = Mockito.mock(TextRequestHandler.class);
		Mockito.when(handler.handle(Mockito.anyString())).thenCallRealMethod();
		Mockito.when(handler.getMessageFactory()).thenReturn(new TextMessageFactory());
		Mockito.when(handler.getStorageServiceBeanName()).thenReturn("mockStorageService");
	}

	@Test
	public void handleTest() {

		Mockito.when(handler.getStoreService()).thenCallRealMethod();
		handler.getStoreService().clear();


		ResponseEntity<String> response = handler.handle("text");
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		Assert.assertEquals(new Integer(1),
				new Integer(handler.getStoreService().getMessages(MessageTypesEnum.TEXT).size()));
		Assert.assertEquals(new Integer(0),
				new Integer(handler.getStoreService().getMessages(MessageTypesEnum.EMOTION).size()));

		ResponseEntity<String> response1 = handler.handle("yet another text");
		assertEquals(HttpStatus.CREATED, response1.getStatusCode());

		assertEquals(new Integer(2), new Integer(handler.getStoreService().getMessages(MessageTypesEnum.TEXT).size()));
		assertEquals(new Integer(0),
				new Integer(handler.getStoreService().getMessages(MessageTypesEnum.EMOTION).size()));
		
		ResponseEntity<String> invalidResponse = handler.handle("");
		assertEquals(HttpStatus.PRECONDITION_FAILED, invalidResponse.getStatusCode());
		
		assertEquals(new Integer(2), new Integer(handler.getStoreService().getMessages(MessageTypesEnum.TEXT).size()));
		assertEquals(new Integer(0),
				new Integer(handler.getStoreService().getMessages(MessageTypesEnum.EMOTION).size()));


	}

	@Test
	public void handleStorageFailure() throws StorageFailureException{
		IMessageStoreService mockStoreService = Mockito.mock(IMessageStoreService.class);
		Mockito.doThrow(StorageFailureException.class).when(mockStoreService).store(Mockito.any(IMessage.class));
		
		Mockito.when(handler.getStoreService()).thenReturn(mockStoreService);
		
		ResponseEntity<String> response = handler.handle("text");
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

		assertEquals(new Integer(0), new Integer(handler.getStoreService().getMessages(MessageTypesEnum.TEXT).size()));
		assertEquals(new Integer(0),
				new Integer(handler.getStoreService().getMessages(MessageTypesEnum.EMOTION).size()));

	}
}
