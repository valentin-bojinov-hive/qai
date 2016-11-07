package com.qaiware.task.bojinov.model.factory.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.MessageTypesEnum;
import com.qaiware.task.bojinov.model.factory.AbstractValidatingMessageFactory;
import com.qaiware.task.bojinov.model.impl.EmotionMessage;

/**
 * Message factory for creating valid Emotions
 * @author valio
 *
 */
public class EmotionMessageFactory extends AbstractValidatingMessageFactory {

	static final int MIN_LENGTH = 2;
	static final int MAX_LENGTH = 10;

	@Override
	public String getMessageType() {
		return MessageTypesEnum.EMOTION.type();
	}

	@Override
	protected boolean isValidMessage(final String message) {

		if (!validate(message, MIN_LENGTH, MAX_LENGTH)) {
			return false;
		}

		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(message);

		return !matcher.find();

	}

	@Override
	protected IMessage createValidMessage(final String validatedMessage) {
		return new EmotionMessage(validatedMessage);
	}

}
