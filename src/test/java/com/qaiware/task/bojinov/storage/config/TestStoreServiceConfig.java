package com.qaiware.task.bojinov.storage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qaiware.task.bojinov.model.factory.impl.EmotionMessageFactory;
import com.qaiware.task.bojinov.model.factory.impl.TextMessageFactory;
import com.qaiware.task.bojinov.storage.IMessageStoreService;
import com.qaiware.task.bojinov.storage.impl.MockDatabaseMessageStoreService;

@Configuration
public class TestStoreServiceConfig {
	@Bean
	public IMessageStoreService getStoreService()
	{
		return new MockDatabaseMessageStoreService();
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
