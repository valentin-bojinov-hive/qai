package com.qaiware.task.bojinov.model.factory;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.exceptions.MessageException;
/**
 * Interface for creating {@link IMessage} messages
 * @author valio
 *
 */
public interface IMessageFactory {
	IMessage createMessage(String message) throws MessageException;
	String getMessageType();
}
