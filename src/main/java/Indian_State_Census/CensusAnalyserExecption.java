package Indian_State_Census;

@SuppressWarnings("serial")
public class CensusAnalyserExecption extends Exception {
	public enum ExceptionType {
		NO_FILE, INCORRECT_FILE, UNABLE_TO_PARSE
	}

	public ExceptionType type;

	public CensusAnalyserExecption(String message, ExceptionType type) {
		// TODO Auto-generated constructor stub
		super(message);
		this.type = type;
	}
}

