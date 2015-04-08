package core.exception;

public class IsNotValidatedMail extends Exception {
	private static final long serialVersionUID = 1L;

	public IsNotValidatedMail(String message) {
		super(message);
	}
}
