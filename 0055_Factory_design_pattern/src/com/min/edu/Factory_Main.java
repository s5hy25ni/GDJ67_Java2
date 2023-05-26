package com.min.edu;

public class Factory_Main {

	/**
	 * TODO 008 
	 */
	public static void main(String[] args) {
		// 생성하고 등록 기능의 클래스를 생성
		Factory fc = new IDCardFactory();
		
		// 개인사용자를 만듬
		Product card1 = fc.create("법인카드");
		Product card2 = fc.create("개인카드");
		
		// 카드 사용
		card1.use();
		card2.use();
		
		// 관리자 기능 전체 등록 사용자 검색
		IDCardFactory search_Card = (IDCardFactory)fc;
		System.out.println(search_Card.getOwners());
		
	}

}





