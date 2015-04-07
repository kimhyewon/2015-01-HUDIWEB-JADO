package jado.controller;

public class ResultValue {
	private String key;
	private Object value;
	
	public ResultValue(String key, Object value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public Object getValue() {
		return this.value;
	}
}
