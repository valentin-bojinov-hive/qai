package com.qaiware.task.bojinov.model.impl;

import com.qaiware.task.bojinov.model.MessageTypesEnum;
/**
 * Class for Emotion messages
 * @author valio
 *
 */
public class EmotionMessage extends AbstractMessage {

	public EmotionMessage(final String message) {
		super(message, MessageTypesEnum.EMOTION);
	}
}
