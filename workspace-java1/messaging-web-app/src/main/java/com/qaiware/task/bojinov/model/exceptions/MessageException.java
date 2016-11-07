package com.qaiware.task.bojinov.model.exceptions;
/**
 * Generic Exception indicating problem with a message
 * @author valio
 *
 */
public class MessageException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MessageException (String message)
	{
		super(message);
	}
	
	public MessageException (String message, Exception cause)
	{
		super(message, cause);
	}

}
