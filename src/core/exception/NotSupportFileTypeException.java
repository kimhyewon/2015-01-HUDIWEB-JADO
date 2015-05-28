package core.exception;

public class NotSupportFileTypeException extends ForignKeyException {
	private static final long serialVersionUID = 1L;
	
	public NotSupportFileTypeException(String message) {
		super(message);
	}
}
