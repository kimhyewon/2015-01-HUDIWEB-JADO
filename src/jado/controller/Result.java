package jado.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;

public class Result {
	private boolean success;
	private String errorMessage;
	private Map<String, Object> resultMap  = new HashMap<>();
	
	public Result (boolean success, ResultValue... resultValues) {
		this.success = success;
		
		for(ResultValue resultValue : resultValues) {
			resultMap.put(resultValue.getKey(), resultValue.getValue());
		}
	}
	
	public Result(boolean success, String errorMessage) {
		this.success = success;
		this.errorMessage = errorMessage;
	}
	
	public Result(boolean success) {
		this.success = success;
		this.errorMessage = null;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void addErrorMessage(Model model) {
		if(success) {
			return;
		}

		model.addAttribute("errorMessage", errorMessage);
	}
	
	public Object getValue(String key) {
		return resultMap.get(key);
	}
}
