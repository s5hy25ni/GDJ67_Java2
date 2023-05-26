package com.min.edu;

public class NoMasicException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5225993831844052481L;

	public NoMasicException() {
		this("입력하신 숫자는 마방진을 만들지 못합니다.");
	}

	public NoMasicException(String message) {
		super(message);
	}
	
	

}
