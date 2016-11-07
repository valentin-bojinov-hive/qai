package com.qaiware.task.bojinov.webapp.handler.impl;

import com.qaiware.task.bojinov.model.factory.AbstractValidatingMessageFactory;
import com.qaiware.task.bojinov.model.factory.impl.EmotionMessageFactory;

public class EmotionRequestHandler extends AbstractRequestHandler {

	private final AbstractValidatingMessageFactory factory = new EmotionMessageFactory();
	
	@Override
	protected AbstractValidatingMessageFactory getMessageFactory() {
		return factory;
	}

}
