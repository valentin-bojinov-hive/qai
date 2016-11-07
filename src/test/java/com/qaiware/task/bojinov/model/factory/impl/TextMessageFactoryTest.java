package com.qaiware.task.bojinov.model.factory.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.MessageTypesEnum;
import com.qaiware.task.bojinov.model.exceptions.InvalidMessageException;
import com.qaiware.task.bojinov.model.impl.TextMessage;

public class TextMessageFactoryTest {

	private TextMessageFactory instance;

	@Before
	public void setup() {
		instance = new TextMessageFactory();
	}

	@Test
	public void createValidMessageTest() throws InvalidMessageException {
		
		String textMessage = "sample message";
		IMessage message = instance.createMessage(textMessage);
		assertTrue(message instanceof TextMessage);
		assertEquals(textMessage, message.getMessage());
		assertEquals(MessageTypesEnum.TEXT, message.getType());
		
		String shortestValidMessage = "A";
		IMessage shortestMessage = instance.createMessage(shortestValidMessage);
		
		assertTrue(shortestMessage instanceof TextMessage);
		assertEquals(shortestValidMessage, shortestMessage.getMessage());
		assertEquals(MessageTypesEnum.TEXT, message.getType());
			
		StringBuilder sb = new StringBuilder();
		sb.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789");
		
		String longestValidMessage = sb.toString();
		IMessage longestMessage = instance.createMessage(longestValidMessage);
		
		assertTrue(longestMessage instanceof TextMessage);
		assertEquals(longestValidMessage, longestMessage.getMessage());
		assertEquals(MessageTypesEnum.TEXT, message.getType());
	}
	
	@Test(expected=InvalidMessageException.class)
	public void createInvalidTooShortMessageTest() throws InvalidMessageException {
		
		String message = "";
		instance.createMessage(message);
	}
	
	@Test(expected=InvalidMessageException.class)
	public void createInvalidTooLongMessageTest() throws InvalidMessageException {
		
		StringBuilder sb = new StringBuilder();
		sb.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0123456789")
			.append("0");
		
		String message = sb.toString();
		
		instance.createMessage(message);
	}
	
}
