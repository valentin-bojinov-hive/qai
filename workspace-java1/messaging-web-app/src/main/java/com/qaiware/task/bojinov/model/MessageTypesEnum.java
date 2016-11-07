package com.qaiware.task.bojinov.model;

/**
 * All supported message types are enumerated here
 * @author valio
 *
 */
public enum MessageTypesEnum {

	EMOTION("Emotion"),
	TEXT("Text");
	
	final String type;
	MessageTypesEnum(String type)
	{
		this.type = type;
	}
	
	public String type()
	{
		return type;
	}

	
	


}
