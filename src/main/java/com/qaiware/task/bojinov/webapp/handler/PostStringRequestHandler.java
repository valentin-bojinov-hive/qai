package com.qaiware.task.bojinov.webapp.handler;

import org.springframework.http.ResponseEntity;

/**
 * The PostStringRequestHandler is responsible for creating a valid message and storing it to the database 
 * or returning an appropriate http status code in case of an error
 * @author valio
 *
 */
public interface PostStringRequestHandler {
	public ResponseEntity<String> handle(String payload);

}
