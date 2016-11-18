package com.qaiware.task.bojinov.webapp.handler.impl;

import org.springframework.context.support.AbstractApplicationContext;

import com.qaiware.task.bojinov.model.factory.AbstractValidatingMessageFactory;
import com.qaiware.task.bojinov.model.factory.impl.TextMessageFactory;

public class TextRequestHandler extends AbstractRequestHandler {

	private final AbstractValidatingMessageFactory factory;

	public TextRequestHandler(AbstractApplicationContext applicationContext) {
		super(applicationContext);
		factory = applicationContext.getBean(TextMessageFactory.class);

	}

	
	@Override
	protected AbstractValidatingMessageFactory getMessageFactory() {
		return factory;
	}
}
