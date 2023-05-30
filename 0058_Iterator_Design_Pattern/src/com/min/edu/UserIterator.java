package com.min.edu;

import java.util.Iterator;

public class UserIterator implements Iterator{
	
	private UserList userList;
	private int index;

	public UserIterator(UserList userList) {
		super();
		this.userList = userList;
		index = 0;
	}

	@Override
	public boolean hasNext() {
		if(index < userList.size()) {
			return true;
		} else {
			return false;			
		}
	}

	@Override
	public Object next() {
		User user = userList.get(index++);
		return user;
	}

}
