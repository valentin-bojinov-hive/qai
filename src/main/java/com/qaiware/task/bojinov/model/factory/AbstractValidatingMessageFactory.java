package com.qaiware.task.bojinov.model.factory;

import com.qaiware.task.bojinov.model.IMessage;
import com.qaiware.task.bojinov.model.exceptions.InvalidMessageException;

/**
 * AbstractFactory assuring that {@link InvalidMessageException} is thrown if a
 * message is not valid
 * 
 * @author valio
 *
 */
public abstract class AbstractValidatingMessageFactory implements IMessageFactory {

	protected void validate(final String message) throws InvalidMessageException {
		if (!isValidMessage(message)) {
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("'").append(message).append("'").append(" is not a valid ").append(getMessageType())
					.append(" message");
			throw new InvalidMessageException(errorMessage.toString());
		}
	}

	protected boolean validate(final String message, int minLength, int maxLength) {
		return message.length() >= minLength && message.length() <= maxLength;
	}

	/**
	 * Specifies whether a message is valid or not
	 * 
	 * @param message
	 * @return
	 */
	protected abstract boolean isValidMessage(final String message);

	@Override
	public IMessage createMessage(final String message) throws InvalidMessageException {
		validate(message);
		return createValidMessage(message);
	}

	/**
	 * Should be called only for creating valid messges
	 * 
	 * @param validatedMessage
	 * @return
	 */
	protected abstract IMessage createValidMessage(final String validatedMessage);

}
