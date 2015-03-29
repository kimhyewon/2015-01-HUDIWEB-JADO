package core.mail;

public enum MailType {
	// Join Type
	JOIN_VERIFY(1), JOIN_WELCOME(2);
	
	private int value;
	
	private MailType (int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
