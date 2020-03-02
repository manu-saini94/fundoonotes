package com.bridgelabz.fundoo_note_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotesNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotesNotFoundException(String message) {
		super(message);
	}
}
