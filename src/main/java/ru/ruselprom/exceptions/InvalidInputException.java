package ru.ruselprom.exceptions;

@SuppressWarnings("serial")
public class InvalidInputException extends RuntimeException {
	public InvalidInputException(String errorMessage) {
		super(errorMessage);
	}
}
