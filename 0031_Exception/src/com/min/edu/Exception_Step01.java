package com.min.edu;

import java.util.Scanner;

public class Exception_Step01 {

	// 분모값이 0이면 발생되는 예외 클래스 : java.lang.ArithmeticException: / by zero
	// 정수가 아닌 값을 입력면 발생되는 예외 클래스 : java.util.InputMismatchException
	
	public int calculation() {
		int n = 0;
			Scanner scan = new Scanner(System.in);
			System.out.println("정수를 입력해 주세요");
			n = scan.nextInt() / scan.nextInt();
			System.out.println("연산결과 : " + n);
		return n;
	}
	
}





