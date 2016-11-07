package com.qaiware.task.bojinov.model;

/**
 * Interface for the messages supported by the POST server
 * @author valio
 *
 */
public interface IMessage {
	String getMessage();
	MessageTypesEnum getType();
}
