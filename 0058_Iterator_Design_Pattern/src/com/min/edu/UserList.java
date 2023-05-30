package com.min.edu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * User 클래스 한 개의 객체를 Collector 하는 객체
 * Aggregate를 implements하여 Iterator를 구현
 */
public class UserList implements Aggregate {
	private List<User> userList;
	
	public UserList() {
		userList = new ArrayList<User>();
	}
	
	public void add(User user) {
		userList.add(user);
	}
	
	public User get(int index) {
		return userList.get(index);
	}
	
	public int size() {
		return userList.size();
	}

	@Override
	public Iterator iterator() {
		return new UserIterator(this);
	}
	
	
}
