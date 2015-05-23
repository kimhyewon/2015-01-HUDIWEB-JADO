package jado.model;

public class Notice {
	private String header;
	private String message;

	public Notice() {
	}

	public Notice(String header, String message) {
		super();
		this.header = header;
		this.message = message;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
