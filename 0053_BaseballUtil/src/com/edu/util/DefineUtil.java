package com.edu.util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 숫자야구게임에서 사용할 데이터를 입력 받는 클래스
 * @author Jeon Min Kyun
 * @since 2023. 05. 18
 *
 */
public final class DefineUtil {

	/**
	 * <b>java.util.Scanner</b> 객체를 사용하여 키보드로 입력받는 값을 int로 반환하는 메소드<br>
	 * 예외처리를 통한 정확한 값을 입력 받을 수 있다
	 * @return 정수(int) 범위의 값
	 */
	@SuppressWarnings("resource")
	public static int getNum() {
		int n = 0;
		while (true) {
			try {
				System.out.println("정수만 입력해 주세요");
				Scanner scan = new Scanner(System.in);
				n = scan.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("잘못된 타입의 값입니다. \n 재입력 해주세요 / " + e.getMessage());
				//			e.printStackTrace();
			} 
		}
		return n;
	}
	
	/**
	 * <b>java.util.Scanner</b> 객체를 사용하여 키보드로 입력받는 값을 String로 반환하는 메소드<br>
	 * 한줄의 값을 입력받아, 앞뒤의 공백(Blank)를 제거하여 문자열 값만 반환한다.<br>
	 * It is a Unicode space character (
	 * <a href='https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html#SPACE_SEPARATOR'>SPACE_SEPARATOR</a>, 
	 * <a href='https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html#LINE_SEPARATOR'>LINE_SEPARATOR</a>, or 
	 * <a href='https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html#PARAGRAPH_SEPARATOR'>PARAGRAPH_SEPARATOR</a>) but is
	 * not also a non-breaking space ('\u00A0', '\u2007', '\u202F').
	 * @return 문자열 값
	 */
	@SuppressWarnings("resource")
	public static String getString() {
		String str = "";
		System.out.println("필요 값을 입력해주세요");
		Scanner scan = new Scanner(System.in);
		str = scan.nextLine(); // 가 나 다 
		return str.strip();
	}
	
	@SuppressWarnings("resource")
	public static String getAnswer(String msg) {
		String answer = "";
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println(msg);
			answer = scan.nextLine().strip();
			
			if(answer.compareToIgnoreCase("y")==0 ||
					answer.compareToIgnoreCase("n")==0	) {
				break;
			}
		}
		return answer;
	}
	
	
}






