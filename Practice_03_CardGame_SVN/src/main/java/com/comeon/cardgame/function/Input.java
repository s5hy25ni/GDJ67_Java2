package com.comeon.cardgame.function;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Input {

	/**
	 * 설정한 범위 외 숫자를 입력하거나 정수 외의 값을 입력시 재입력
	 * @param msg 출력 메세지
	 * @param start 선택 가능한 int 중 가장 작은 int
	 * @param end 선택 가능한 int 중 가장 높은 int
	 * @return 유저가 선택한 int
	 */
	@SuppressWarnings("resource")
	public static int getNum(String msg, int start, int end) {
		int answer = 0;
		while (true) {
			try {
				System.out.printf(msg);
				Scanner scan = new Scanner(System.in);
				answer = scan.nextInt();
				if (answer < start || answer > end) {
					System.out.printf(" ▷ %d부터 %d까지 입력해주세요!\n", start, end);
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println(" ▷ 정수만 입력해주세요.");
				System.out.println();
			}
		}
		return answer;
	}
	
	/**
	 * 설정한 특정 숫자 외 숫자를 입력시 재입력<br>
	 * 여러개 입력 가능<br>
	 * 1,2,4가 선택 가능하면 3 입력하면 재입력
	 * @param msg 출력 메세지
	 * @param able 가능한 int들 list
	 * @return 유저 선택 숫자
	 */
	@SuppressWarnings("resource")
	public static int getNum(String msg, List<Integer> able) {
		int answer = 0;
		while (true) {
			try {
				System.out.printf(msg);
				Scanner scan = new Scanner(System.in);
				answer = scan.nextInt();
				if (!able.contains(answer)) {
					System.out.printf(" ▷ "+able+"중 하나만 입력해주세요!\n");
					System.out.println();
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println(" ▷ 정수만 입력해주세요.");
				System.out.println();
			}
		}
		return answer;
	}

	/**
	 * 
	 * @param msg 출력 메세지
	 * @param min 최소 글자수
	 * @param max 최대 글자수
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String getString(String msg, int min, int max) {
		String answer = "";
		while (true) {
			System.out.printf(msg);
			Scanner scan = new Scanner(System.in);
			answer = scan.nextLine(); // 가 나 다
			answer.strip();
			if (answer.length() < min || answer.length() > max) {
				System.out.printf(" ▷ %d 글자 이상 %d 글자 이하로 입력해주세요!\n", min, max);
				System.out.println();
			} else {
				return answer;
			}
		}
	}

	/**
	 * 여러 개의 숫자 입력 받음, 범위 외 숫자 입력시 재입력
	 * @return
	 */
	@SuppressWarnings("resource")
	public static List<Integer> getIntArray(String msg, List<Integer> range) {
		String answer = "";
		while (true) {
			System.out.printf(msg);
			Scanner scan = new Scanner(System.in);
			answer = scan.nextLine();
			answer.strip();
			List<String> strTemp = new ArrayList<String>(List.of(answer.split("\u0020")));
			List<Integer> intTemp = new ArrayList<Integer>();
			boolean isc = false;
			for (String string : strTemp) {
				if (!range.contains(Integer.parseInt(string))) {
					System.out.println(" ▷ "+range + " 중에서 선택해주세요.");
					System.out.println();
					isc = true;
					break;
				}
			}
			if(!isc) {
				for (String string : strTemp) {
					intTemp.add(Integer.parseInt(string));
				}
				return intTemp;				
			}
		}
	}

	/**
	 * 오직 Y(y)나 N(n)만 입력 받음
	 */
	@SuppressWarnings("resource")
	public static boolean getYerOrNo(String msg) {
		String answer = "";
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.printf(msg);
			answer = scan.nextLine().strip();

			if (answer.compareToIgnoreCase("y") == 0) {
				return true;
			} else if (answer.compareToIgnoreCase("n") == 0) {
				return false;
			} else {
				System.out.println(" ▷ Y(y) 또는 N(n)만 입력해주세요!");
				System.out.println();
			}
		}
	}

}
