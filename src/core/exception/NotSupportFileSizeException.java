package core.exception;

public class NotSupportFileSizeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NotSupportFileSizeException(String message) {
		super(message);
	}
}
