package org.sagarmala.exception;

public class CustomException extends Exception {

	public CustomException(String s) {

		System.err.println("File path cannot be null! Please provide a path");

	}

}
