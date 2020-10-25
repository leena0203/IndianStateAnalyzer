package com.IndianStateCensus;

@SuppressWarnings("serial")
public class CensusAnalyserExecption extends Exception {
	public enum ExceptionType {
		NO_FILE, INCORRECT_FILE
	}

	public ExceptionType type;

	public CensusAnalyserExecption(String message, ExceptionType type) {
		// TODO Auto-generated constructor stub
		super(message);
		this.type = type;
	}
}

