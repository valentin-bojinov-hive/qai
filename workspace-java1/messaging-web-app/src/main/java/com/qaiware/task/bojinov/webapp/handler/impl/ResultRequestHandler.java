package com.qaiware.task.bojinov.webapp.handler.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.factory.AbstractValidatingMessageFactory;

public class ResultRequestHandler extends AbstractRequestHandler {

	@Override
	protected AbstractValidatingMessageFactory getMessageFactory() {
		return null;
	}
	
	public ResponseEntity<String> handle()
	{
		List<IMessage> messages = getStoreService().getMessages();
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
