package com.qaiware.task.bojinov.webapp.handler.impl;

import org.springframework.context.support.AbstractApplicationContext;

import com.qaiware.task.bojinov.model.factory.AbstractValidatingMessageFactory;
import com.qaiware.task.bojinov.model.factory.impl.EmotionMessageFactory;

public class EmotionRequestHandler extends AbstractRequestHandler {

	private final AbstractValidatingMessageFactory factory;

	public EmotionRequestHandler(AbstractApplicationContext applicationContext) {
		super(applicationContext);
		factory = applicationContext.getBean(EmotionMessageFactory.class);
	}

	
	@Override
	protected AbstractValidatingMessageFactory getMessageFactory() {
		return factory;
	}

}
