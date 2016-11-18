package com.qaiware.task.bojinov.webapp.handler.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.storage.IMessageStoreService;
import com.qaiware.task.bojinov.webapp.handler.GetRequestHandler;

public class ResultRequestHandler implements GetRequestHandler {
	
	

	private final AbstractApplicationContext applicationContext;



	public ResultRequestHandler(AbstractApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	
	
	public ResponseEntity<String> handle()
	{
		List<IMessage> messages = applicationContext.getBean(IMessageStoreService.class).getMessages();
		StringBuilder sb = new StringBuilder();
		sb.append("Total messages: ").append(messages.size()).append("\n");
		Iterator<IMessage> iterator = messages.iterator();
		while(iterator.hasNext())
		{
			IMessage message = iterator.next();
			sb.append("{[").append(message.getType().type()).append("]").append(message.getMessage()).append("}");
			if (iterator.hasNext())
			{
				sb.append(", ");
			}
		}
		return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);


	}

}
