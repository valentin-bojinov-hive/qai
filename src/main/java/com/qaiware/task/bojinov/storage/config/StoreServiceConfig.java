package com.qaiware.task.bojinov.storage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qaiware.task.bojinov.storage.IMessageStoreService;
import com.qaiware.task.bojinov.storage.impl.DatabaseMessageStoreService;

@Configuration
public class StoreServiceConfig {
	@Bean
	public IMessageStoreService storeService()
	{
		return new DatabaseMessageStoreService();
	}
}
