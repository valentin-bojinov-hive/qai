package com.qaiware.task.bojinov.model.factory.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.MessageTypesEnum;
import com.qaiware.task.bojinov.model.exceptions.InvalidMessageException;
import com.qaiware.task.bojinov.model.impl.EmotionMessage;

public class EmotionMessageFactoryTest {

	private EmotionMessageFactory instance;

	@Before
	public void setup() {
		instance = new EmotionMessageFactory();
	}

	@Test
	public void createValidMessageTest() throws InvalidMessageException {
		
		String textMessage = "emotion";
		IMessage message = instance.createMessage(textMessage);
		assertTrue(message instanceof EmotionMessage);
		assertEquals(textMessage, message.getMessage());
		assertEquals(MessageTypesEnum.EMOTION, message.getType());
		
		String shortestValidMessage = "AB";
		IMessage shortestMessage = instance.createMessage(shortestValidMessage);
		
		assertTrue(shortestMessage instanceof EmotionMessage);
		assertEquals(shortestValidMessage, shortestMessage.getMessage());
		assertEquals(MessageTypesEnum.EMOTION, message.getType());
		
		String longestValidMessage = "ABCDEFGHIJ";
		IMessage longestMessage = instance.createMessage(longestValidMessage);
		
		assertTrue(longestMessage instanceof EmotionMessage);
		assertEquals(longestValidMessage, longestMessage.getMessage());
		assertEquals(MessageTypesEnum.EMOTION, message.getType());
	}
	
	@Test(expected=InvalidMessageException.class)
	public void createInvalidTooShortMessageTest() throws InvalidMessageException {
		
		String message = "A";
		instance.createMessage(message);
	}
	
	@Test(expected=InvalidMessageException.class)
	public void createInvalidTooLongMessageTest() throws InvalidMessageException {			
		
		String longerInvalidMessage = "ABCDEFGHIJK";		
		instance.createMessage(longerInvalidMessage);
	}
	
	@Test(expected=InvalidMessageException.class)
	public void createInvalidMessageTest() throws InvalidMessageException {
		
		String message = "0A";
		instance.createMessage(message);
	}
	
	@Test(expected=InvalidMessageException.class)
	public void createInvalidMessageTest1() throws InvalidMessageException {
		
		String message = "ABCD1ZZZ";
		instance.createMessage(message);
	}
	
}
