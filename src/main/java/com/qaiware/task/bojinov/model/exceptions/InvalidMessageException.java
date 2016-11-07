package com.qaiware.task.bojinov.model.exceptions;

/**
 * Exception indicating that a message is not valid
 * @author valio
 *
 */
public class InvalidMessageException extends MessageException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidMessageException (String message)
	{
		super(message);
	}
	
	public InvalidMessageException (String message, Exception cause)
	{
		super(message, cause);
	}

}
