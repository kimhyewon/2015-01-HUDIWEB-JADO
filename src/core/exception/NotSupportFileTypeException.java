package core.exception;

public class NotSupportFileTypeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NotSupportFileTypeException(String message) {
		super(message);
	}
}
