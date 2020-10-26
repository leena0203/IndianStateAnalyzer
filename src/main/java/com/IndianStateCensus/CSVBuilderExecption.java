package com.IndianStateCensus;

public class CSVBuilderExecption extends Exception {
	public enum ExceptionType {
		NO_FILE, INCORRECT_FILE, UNABLE_TO_PARSE
	}

	public ExceptionType type;

	public CSVBuilderExecption(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

}

