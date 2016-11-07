package com.qaiware.task.bojinov.storage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.MessageTypesEnum;
import com.qaiware.task.bojinov.storage.IMessageStoreService;
import com.qaiware.task.bojinov.storage.exceptions.StorageFailureException;

@Service
public class MockDatabaseMessageStoreService implements IMessageStoreService {

	private final Map<String, List<IMessage>> store = new HashMap<String, List<IMessage>>();
	
	
	public MockDatabaseMessageStoreService()
	{
		

	}
	
	@Override
	public void store(IMessage message) throws StorageFailureException {
		System.out.println("Mock Storage for " + message.getMessage());
		
		store.putIfAbsent(MessageTypesEnum.EMOTION.type(), new ArrayList<IMessage>());
		store.putIfAbsent(MessageTypesEnum.TEXT.type(), new ArrayList<IMessage>());

		store.get(message.getType().type()).add(message);
	}

	@Override
	public List<IMessage> getMessages() {
		store.putIfAbsent(MessageTypesEnum.EMOTION.type(), new ArrayList<IMessage>());
		store.putIfAbsent(MessageTypesEnum.TEXT.type(), new ArrayList<IMessage>());
		List<IMessage> list = new ArrayList<IMessage>();
		list.addAll(store.get(MessageTypesEnum.EMOTION.type()));
		list.addAll(store.get(MessageTypesEnum.TEXT.type()));
		return list;
	}

	@Override
	public void clear() {
		store.clear();
	}

	@Override
	public List<IMessage> getMessages(MessageTypesEnum type) {
		List<IMessage> filteredList = new ArrayList<IMessage>();
		for (IMessage message : getMessages())
		{
			if (message.getType().equals(type))
			{
				filteredList.add(message);
			}
		}
		return filteredList;
	}
	
	

}
