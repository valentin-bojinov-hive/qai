package com.qaiware.task.bojinov.webapp.handler.impl;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.exceptions.InvalidMessageException;
import com.qaiware.task.bojinov.model.exceptions.MessageException;
import com.qaiware.task.bojinov.model.factory.IMessageFactory;
import com.qaiware.task.bojinov.storage.IMessageStoreService;
import com.qaiware.task.bojinov.storage.exceptions.StorageFailureException;
import com.qaiware.task.bojinov.webapp.handler.PostStringRequestHandler;

public class RequestHandler implements PostStringRequestHandler {

	/**
	 * static {
		 applicationContext = new GenericApplicationContext();
		 XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(applicationContext);
		 xmlReader.loadBeanDefinitions(new ClassPathResource("spring/services.xml"));
		 applicationContext.refresh();
	}
	 */
	
	private final AbstractApplicationContext applicationContext;
	private final IMessageFactory messageFactory;
	
	public RequestHandler(final AbstractApplicationContext applicationContext, final IMessageFactory messageFactory) {
		this.applicationContext = applicationContext;
		this.messageFactory = messageFactory;
	}
	
	
	/**
	 * Creates a valid Message and stores it to the database if the message is not valid 
	 * returns PRECODITION FAILED, in case of db error returns INTERNAL_SERVER_ERROR
	 * Otherwise CREATED
	 */
	@Override
	public ResponseEntity<String> handle(final String payload) {
		
		try {
			
			IMessage message = getMessageFactory().createMessage(payload);
			getStoreService().store(message);
			
		} catch (InvalidMessageException e) {
			return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
		} catch (MessageException e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		catch (StorageFailureException e)
		{
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}	
	

	protected IMessageStoreService getStoreService() {
		
		return applicationContext.getBean(IMessageStoreService.class);
	}
	
	

	protected IMessageFactory getMessageFactory() {
		 return messageFactory;
	}

}
