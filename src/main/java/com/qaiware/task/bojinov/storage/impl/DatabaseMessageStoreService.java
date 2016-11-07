package com.qaiware.task.bojinov.storage.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.MessageTypesEnum;
import com.qaiware.task.bojinov.model.impl.EmotionMessage;
import com.qaiware.task.bojinov.model.impl.TextMessage;
import com.qaiware.task.bojinov.storage.IMessageStoreService;
import com.qaiware.task.bojinov.storage.entity.MessageEntity;
import com.qaiware.task.bojinov.storage.exceptions.StorageFailureException;

/**
 * Service for storing Messages to the database
 * 
 * @author valio
 *
 */
@Service
public class DatabaseMessageStoreService implements IMessageStoreService {

	@Override
	public void store(final IMessage message) throws StorageFailureException {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DefaultPersistenceUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(toMessageEntity(message));
		entityManager.getTransaction().commit();

	}

	@Override
	public List<IMessage> getMessages() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DefaultPersistenceUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("from MessageEntity");
		List<IMessage> result = new ArrayList<IMessage>(query.getResultList().size());
		for (MessageEntity entity : (List<MessageEntity>) query.getResultList()) {
			if (entity.getType().equals(MessageTypesEnum.EMOTION.type())) {
				result.add(new EmotionMessage(entity.getPayload()));

			}

			if (entity.getType().equals(MessageTypesEnum.TEXT.type())) {
				result.add(new TextMessage(entity.getPayload()));

			}

		}
		return result;
	}

	@Override
	public void clear() {
		// XXX Used only for the Unit test implementation
	}

	private MessageEntity toMessageEntity(final IMessage message) {
		MessageEntity entity = new MessageEntity();
		entity.setPayload(message.getMessage());
		entity.setType(message.getType().type());
		entity.setCreatedDate(new Date());
		return entity;
	}

	@Override
	public List<IMessage> getMessages(MessageTypesEnum type) {
		List<IMessage> filteredList = new ArrayList<IMessage>();
		for (IMessage message : getMessages()) {
			if (message.getType().equals(type)) {
				filteredList.add(message);
			}
		}
		return filteredList;
	}

}
