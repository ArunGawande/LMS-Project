package com.bridgelabz.lmsprojects.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ContentNotFoundException extends RuntimeException{

	public ContentNotFoundException(String string) {
		super(string);
		this.message=string;
	}

	private String message;

}
