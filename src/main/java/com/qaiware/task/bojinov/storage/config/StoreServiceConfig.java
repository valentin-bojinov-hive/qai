package com.qaiware.task.bojinov.storage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qaiware.task.bojinov.model.factory.impl.EmotionMessageFactory;
import com.qaiware.task.bojinov.model.factory.impl.TextMessageFactory;
import com.qaiware.task.bojinov.storage.IMessageStoreService;
import com.qaiware.task.bojinov.storage.impl.DatabaseMessageStoreService;

@Configuration
public class StoreServiceConfig {
	@Bean
	public IMessageStoreService storeService()
	{
		return new DatabaseMessageStoreService();
	}
	
	@Bean
	public EmotionMessageFactory emotionMessageFactory()
	{
		return new EmotionMessageFactory();
	}
	
	@Bean
	public TextMessageFactory textMessageFactory()
	{
		return new TextMessageFactory();
	}
}
