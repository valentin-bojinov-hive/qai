package com.qaiware.task.bojinov.webapp.handler.impl;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.exceptions.InvalidMessageException;
import com.qaiware.task.bojinov.model.factory.AbstractValidatingMessageFactory;
import com.qaiware.task.bojinov.storage.IMessageStoreService;
import com.qaiware.task.bojinov.storage.exceptions.StorageFailureException;
import com.qaiware.task.bojinov.webapp.handler.IRequestHandler;

public abstract class AbstractRequestHandler implements IRequestHandler {

	private static final String STORAGE_SERVICE = "storageService";
	private static GenericApplicationContext applicationContext;
	
	static {
		 applicationContext = new GenericApplicationContext();
		 XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(applicationContext);
		 xmlReader.loadBeanDefinitions(new ClassPathResource("spring/services.xml"));
		 applicationContext.refresh();
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
		}
		catch (StorageFailureException e)
		{
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<String> handle() {
		return null;
	}

	protected IMessageStoreService getStoreService() {
		
		return applicationContext.getBean(getStorageServiceBeanName(), IMessageStoreService.class);
	}
	
	protected String getStorageServiceBeanName()
	{
		return STORAGE_SERVICE;
	}

	protected abstract AbstractValidatingMessageFactory getMessageFactory();

}
