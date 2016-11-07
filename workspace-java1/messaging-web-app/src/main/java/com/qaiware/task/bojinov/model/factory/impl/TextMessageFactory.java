package com.qaiware.task.bojinov.model.factory.impl;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.MessageTypesEnum;
import com.qaiware.task.bojinov.model.factory.AbstractValidatingMessageFactory;
import com.qaiware.task.bojinov.model.impl.TextMessage;
/**
 * Message factory for creating valid texts
 * @author valio
 *
 */
public class TextMessageFactory extends AbstractValidatingMessageFactory {

	static final int MIN_LENGTH = 1;
	static final int MAX_LENGTH = 160;

	@Override
	public String getMessageType() {
		return MessageTypesEnum.TEXT.type();

	}

	@Override
	protected boolean isValidMessage(final String message) {
		return validate(message, MIN_LENGTH, MAX_LENGTH);
	}

	@Override
	protected IMessage createValidMessage(final String validatedMessage) {
		return new TextMessage(validatedMessage);
	}

}
