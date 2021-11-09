package com.taller.concesionario.dto;

public class ResponseDto {
	private boolean succeeded;
	private String title;
	private String message;
	private Object data;

	public ResponseDto(){}
	public ResponseDto(boolean succeeded, Object data) {
		this.succeeded = succeeded;
		this.data = data;
	}
	public ResponseDto(boolean succeeded, String title, String message, Object data) {
		this.succeeded = succeeded;
		this.title = title;
		this.message = message;
		this.data = data;
	}

	public boolean isSucceeded() {
		return succeeded;
	}
	public void setSucceeded(boolean succeeded) {
		this.succeeded = succeeded;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResponseDTO [succeeded=" + succeeded + ", title=" + title + ", message=" + message + ", data=" + data
				+ "]";
	}
	
}
