package com.qaiware.task.bojinov.webapp.handler.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.MessageTypesEnum;
import com.qaiware.task.bojinov.model.factory.AbstractValidatingMessageFactory;
import com.qaiware.task.bojinov.model.factory.impl.TextMessageFactory;
import com.qaiware.task.bojinov.storage.IMessageStoreService;
import com.qaiware.task.bojinov.storage.config.TestStoreServiceConfig;
import com.qaiware.task.bojinov.storage.exceptions.StorageFailureException;

public class TextRequestHandlerTest {

	private RequestHandler handler;

	@Before
	public void setup() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestStoreServiceConfig.class);
		handler = new RequestHandler(applicationContext, new TextMessageFactory());
	}

	@Test
	public void handleTest() {

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
		
		RequestHandler mockHandler = Mockito.mock(RequestHandler.class);
		Mockito.when(mockHandler.handle(Mockito.anyString())).thenCallRealMethod();
		Mockito.when(mockHandler.getMessageFactory()).thenReturn(Mockito.mock(AbstractValidatingMessageFactory.class));

		Mockito.when(mockHandler.getStoreService()).thenReturn(mockStoreService);
		
		ResponseEntity<String> response = mockHandler.handle("text");
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

		assertEquals(new Integer(0), new Integer(mockHandler.getStoreService().getMessages(MessageTypesEnum.TEXT).size()));
		assertEquals(new Integer(0),
				new Integer(handler.getStoreService().getMessages(MessageTypesEnum.EMOTION).size()));

	}
}
