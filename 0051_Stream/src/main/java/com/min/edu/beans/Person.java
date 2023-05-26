package com.min.edu.beans;

import java.io.Serializable;

public class Person implements Serializable{

	private static final long serialVersionUID = -2046535995140693454L;
	private final Integer id;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String gender;
	private final Integer age;

	public Person(Integer id, String firstName, String lastName, String email, String gender, Integer age) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", gender=" + gender + ", age=" + age + "]";
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public Integer getAge() {
		return age;
	}
}
