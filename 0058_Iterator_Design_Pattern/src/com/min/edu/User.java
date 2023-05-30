package com.min.edu;

// 값을 은닉화하여 멤버필드에 저장
public class User {

	private String name;

	public User(String name) {
			this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
