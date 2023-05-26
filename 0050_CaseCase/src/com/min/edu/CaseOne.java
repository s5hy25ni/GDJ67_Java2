package com.min.edu;

import java.util.Objects;

/**
 * 모양과 숫자를 렌덤으로 조합하여 카드 한장을 만들냄
 *
 */
public class CaseOne {

	private String one;
	
	public static final String[] SHAPE = {"◆", "♥"};
	public static final String[] NUMBER = {"1","3","4","2"};

	public CaseOne() {
		int s = (int)(Math.random()*SHAPE.length);
		int n = (int)(Math.random()*NUMBER.length);
		one = SHAPE[s] + NUMBER[n];
	}

	public String getOne() {
		return one;
	}

	@Override
	public String toString() {
		return String.format("%s",one);
	}

	@Override
	public int hashCode() {
		return Objects.hash(one);
	}

	@Override
	public boolean equals(Object obj) {
		boolean isc = false;
		CaseOne other = (CaseOne) obj;
		if(one.equals(other.getOne())) {
			isc = true;
		}
		return isc;
	}
	
	
	
	
}







