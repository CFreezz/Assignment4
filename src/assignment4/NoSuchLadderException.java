package assignment4;

//Chris Friesen cmf2536
//Malvika Gupta mg42972
//Thursday 2pm 
//3/6/2016
public class NoSuchLadderException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoSuchLadderException(String message) {
		super(message);
	}

	public NoSuchLadderException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
