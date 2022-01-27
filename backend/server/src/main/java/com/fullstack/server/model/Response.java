package com.fullstack.server.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(NON_NULL) // only include values that are not null
public class Response {

	protected LocalDateTime timeStamp; // 
	protected int statusCode; 	
	protected HttpStatus status; 
	protected String reason; 	// reason for the error
	protected String message;  	// will return of the operation was successful or not
	protected String developerMessage; 
	protected Map<?, ?> data; 
	
	
};
