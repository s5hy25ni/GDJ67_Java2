package com.min.edu;

public class DeligateMagicSquare {

	public static void magicSquareLoad(IMagic magic) {
		if(magic != null) {
			magic.make();
			magic.print();
		} else {
			System.out.println("마방진 작성에 실패하였습니다.\n 관리자에게 문의해주세요.");
		}
	}
}
