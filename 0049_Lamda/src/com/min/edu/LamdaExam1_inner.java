package com.min.edu;

/*
 * 람다식 실행 블럭에서는 클래스인 멤버필드와 멤버메소드를 제약 없이 사용가능
 * 람다식 실행 블럭 내에서는 this는 람다식을 실행하는 객체를 참조
 */
public class LamdaExam1_inner {

	public int memberFiled =10; // 인스턴스변수
	
	class Inner{
		int innerField = 20;
		
		void method() {
			MyFunction f = () ->{
				System.out.println("memberFiled : " + memberFiled);
				System.out.println("memberFiled :" + LamdaExam1_inner.this.memberFiled);
				System.out.println("innerField : " + innerField);
				System.out.println("innerField : " + this.innerField);
			};
			f.method();
		}
	}
	
}



