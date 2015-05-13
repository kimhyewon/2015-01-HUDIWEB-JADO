package core.exception;

public class InsertTargetRecordNotFoundException extends ForignKeyException {
	private static final long serialVersionUID = 1L;
	
	public InsertTargetRecordNotFoundException(String message) {
		super(message);
	}
}
