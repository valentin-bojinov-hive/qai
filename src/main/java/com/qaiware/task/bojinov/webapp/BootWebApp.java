package com.qaiware.task.bojinov.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qaiware.task.bojinov.model.factory.impl.EmotionMessageFactory;
import com.qaiware.task.bojinov.model.factory.impl.TextMessageFactory;
import com.qaiware.task.bojinov.storage.config.StoreServiceConfig;
import com.qaiware.task.bojinov.webapp.handler.GetRequestHandler;
import com.qaiware.task.bojinov.webapp.handler.PostStringRequestHandler;
import com.qaiware.task.bojinov.webapp.handler.impl.RequestHandler;
import com.qaiware.task.bojinov.webapp.handler.impl.ResultRequestHandler;

@RestController
@EnableAutoConfiguration
public class BootWebApp {

	private static final  AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(StoreServiceConfig.class);
	public static void main(String[] args) {

		SpringApplication.run(BootWebApp.class, args);
		 

	}

	@RequestMapping(value = "/messages/send_text", method = RequestMethod.POST)
	public ResponseEntity<String> postText(@RequestBody String payload) {
		final PostStringRequestHandler handler = newTextRequestHandler();
		return handler.handle(payload);

	}

	@RequestMapping(value = "messages/send_emotion", method = RequestMethod.POST)
	public ResponseEntity<String> postEmotion(@RequestBody String payload) {
		final PostStringRequestHandler handler = newEmotionRequestHandler();
		return handler.handle(payload);
	}

	@RequestMapping(value = "messages", method = RequestMethod.GET)
	public ResponseEntity<String> postEmotion() {
		final GetRequestHandler handler = newResultRequestHandler();
		return handler.handle();
	}


	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<String> handleError()
	{
		return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
	}


	protected PostStringRequestHandler newTextRequestHandler() {
		return new RequestHandler(applicationContext, new TextMessageFactory());
	}

	protected PostStringRequestHandler newEmotionRequestHandler() {
		return new RequestHandler(applicationContext, new EmotionMessageFactory());
	}

	protected GetRequestHandler newResultRequestHandler() {
		return new ResultRequestHandler(applicationContext);
	}


}
