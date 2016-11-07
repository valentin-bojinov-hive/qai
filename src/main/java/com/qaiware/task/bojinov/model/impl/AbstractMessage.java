package com.qaiware.task.bojinov.model.impl;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.MessageTypesEnum;
/**
 * Base messages class
 * @author valio
 *
 */
public class AbstractMessage implements IMessage {

	private final String message;
	private final MessageTypesEnum type;
	
	protected AbstractMessage(final String message, final MessageTypesEnum type)
	{
		this.message = message;
		this.type = type;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	@Override
	public MessageTypesEnum getType() {
		return type;
	}
	
}
