package com.min.edu.user;

import java.util.Arrays;

/**
 * 컴퓨터가 자동으로 숫자를 생성
 * @author Jeon Min Kyun
 * @since 2023. 05. 18
 */
public class Pitcher extends GamerImpl{
	
	public Pitcher() {
		this(3);
	}

	public Pitcher(int n) {
		super(n);
	}
	
	/**
	 * 난수를 발생 시키는 메소드와 중복 검사 메소드를 사용하여<br> 
	 * 멤버필드의 렌덤숫자의 묶음을 만들어 줌
	 */
	public void make() {
		int idx = 0;
		int n = box.length;
		while (idx != n) {
			int num = makeNum();
			if(!checkBox(num)) {
				box[idx++] = num;
			}
		}
	}
	
	private int makeNum() {
		return (int)(Math.random()*9)+1;
	}
	
	/**
	 *  멤버필드의 Array에서 중복되는 값의 여부를 판단함
	 * @param 무작위 숫자 
	 * @return 중복 true / 중복아님 false
	 */
	private boolean checkBox(int random_num) {
		boolean isc = false;
		for (int i = 0; i < box.length; i++) {
			if(box[i] == random_num) {
				isc = true;
				break;
			}
		}
		return isc;
	}

	
}







