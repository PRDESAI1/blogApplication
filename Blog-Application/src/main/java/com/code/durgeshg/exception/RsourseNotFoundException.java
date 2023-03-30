package com.code.durgeshg.exception;

public class RsourseNotFoundException extends RuntimeException{
	 String resourseName;
	String fieldName;
	long filedVale;
	public RsourseNotFoundException(String resourseName, String fieldName, long filedVale) {
		super(String.format("%s not found with %s:%s",resourseName, fieldName,filedVale));
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.filedVale = filedVale;
	}
	
	
	

}
