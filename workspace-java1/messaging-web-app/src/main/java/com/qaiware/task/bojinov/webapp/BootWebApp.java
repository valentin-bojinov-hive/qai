package com.qaiware.task.bojinov.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qaiware.task.bojinov.webapp.handler.IRequestHandler;
import com.qaiware.task.bojinov.webapp.handler.impl.EmotionRequestHandler;
import com.qaiware.task.bojinov.webapp.handler.impl.ResultRequestHandler;
import com.qaiware.task.bojinov.webapp.handler.impl.TextRequestHandler;

@RestController
@EnableAutoConfiguration
public class BootWebApp {

	public static void main(String[] args) {
	    
		SpringApplication.run(BootWebApp.class, args);

	}

	@RequestMapping(value = "/messages/send_text", method = RequestMethod.POST)
	public ResponseEntity<String> postText(@RequestBody String payload) {
		final IRequestHandler handler = newTextRequestHandler();
		return handler.handle(payload);

	}

	@RequestMapping(value = "messages/send_emotion", method = RequestMethod.POST)
	public ResponseEntity<String> postEmotion(@RequestBody String payload) {
		final IRequestHandler handler = newEmotionRequestHandler();
		return handler.handle(payload);
	}
	
	@RequestMapping(value = "messages", method = RequestMethod.GET)
	public ResponseEntity<String> postEmotion() {
		final IRequestHandler handler = newResultRequestHandler();
		return handler.handle();
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<String> handleError()
	{
		return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
	}
	

	protected IRequestHandler newTextRequestHandler() {
		return new TextRequestHandler();
	}

	protected IRequestHandler newEmotionRequestHandler() {
		return new EmotionRequestHandler();
	}
	
	protected IRequestHandler newResultRequestHandler() {
		return new ResultRequestHandler();
	}

	
}
