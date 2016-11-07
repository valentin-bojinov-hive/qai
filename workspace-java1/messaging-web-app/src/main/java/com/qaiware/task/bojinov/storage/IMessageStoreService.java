package com.qaiware.task.bojinov.storage;

import java.util.List;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.MessageTypesEnum;
import com.qaiware.task.bojinov.storage.exceptions.StorageFailureException;
/**
 * Service interface for handling storage operation of the message
 * @author valio
 *
 */
public interface IMessageStoreService {

	void store(IMessage message) throws StorageFailureException;
	List<IMessage> getMessages(MessageTypesEnum type);
	List<IMessage> getMessages();
	void clear();
}


