package com.qaiware.task.bojinov.model.impl;

import com.qaiware.task.bojinov.model.MessageTypesEnum;
/**
 * class for text messages
 * @author valio
 *
 */
public class TextMessage extends AbstractMessage {

	public TextMessage(final String message) {
		super(message, MessageTypesEnum.TEXT);
	}



}
