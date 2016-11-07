package com.qaiware.task.bojinov.storage.exceptions;
/**
 * Indicating a problem witht the storage
 * @author valio
 *
 */
public class StorageFailureException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StorageFailureException(String message)
	{
		super(message);
	}
	
	public StorageFailureException(String message, Exception cause)
	{
		super(message, cause);
	}

}
