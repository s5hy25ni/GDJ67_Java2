package com.min.edu;

import java.util.Scanner;

public class MagicSquareProcess {

	private static MagicSquareProcess factory;
	private MagicSquareProcess() {
	}
	public static MagicSquareProcess getInstance() {
		if(factory == null) {
			factory = new MagicSquareProcess();
		}
		return factory;
	}
	
	public MagicImpl factory() throws NoMasicException {
		MagicImpl magic = null;	
		int n;
		
		while (true) {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.printf("만들고 싶은 마방진의 숫자를 입력해주세요 : ");
				n = scan.nextInt();
				break;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} // while
		
		if(n<3) {
			throw new NoMasicException("2 이하의 숫자는 마방진이 될 수 없습니다.");
		} else if(n%2==1) { // oddmagic
			magic = new OddMagic(n);
		} else if(n%4==0) { // evenMagic
			magic = new EvenMagic(n);
		} else if(n%4==2) { // sixMagic
			magic = new SixMagicSquare(n);
		}
		
		return magic;
	}
}
