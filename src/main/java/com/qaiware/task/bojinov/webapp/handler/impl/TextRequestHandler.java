package com.qaiware.task.bojinov.webapp.handler.impl;

import com.qaiware.task.bojinov.model.factory.AbstractValidatingMessageFactory;
import com.qaiware.task.bojinov.model.factory.impl.TextMessageFactory;

public class TextRequestHandler extends AbstractRequestHandler {

	private final AbstractValidatingMessageFactory factory = new TextMessageFactory();
	
	@Override
	protected AbstractValidatingMessageFactory getMessageFactory() {
		return factory;
	}
}
