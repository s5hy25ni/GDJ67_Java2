package com.min.edu;

import java.util.Arrays;

import com.edu.util.DefineUtil;
import com.min.edu.play.BaseBallGame;
import com.min.edu.play.Umpire;
import com.min.edu.user.Hitter;
import com.min.edu.user.Pitcher;

public class BaseBallGameMain {

	public static void main(String[] args) {
		// TODO 001 라이브러리 생성 및 테스트
//		System.out.println(DefineUtil.getNum());
//		System.out.println(DefineUtil.getString());
		
		// TODO 002  Pitcher관련 테스트
//		Pitcher p = new Pitcher();
//		int num = p.makeNum();
//		System.out.println(num);
//		boolean isc = p.checkBox(num);
//		System.out.println(isc);
//		boolean isc1 = p.checkBox(-1);
//		System.out.println(isc1);
//		p.make();
//		int[] pitcherBox = p.getBox();
//		System.out.println(Arrays.toString(pitcherBox));
//		System.out.println(p);
//		p.print();
		
		//TODO 003 Hitter관련 테스트
//		Hitter h = new Hitter();
//		boolean isc = h.checkArray();
//		System.out.println(isc);
//		int[] testBox = h.makeNum();
//		System.out.println(Arrays.toString(testBox));
//		h.make();
//		h.print();
//		int[] box = h.getBox();
//		h.print(box);
		
		//TODO 004 비교 클래스 테스트
//		int[] h = {1,2,3};
//		int[] p = {4,5,6}; 
//		Umpire u = new Umpire(p, h);
		
//		Umpire u = new Umpire();
//		u.setpBall(p);
//		u.sethBall(h);
//		
//		int strike = u.getStrike();
//		int ball = u.getBall();
//		
//		System.out.printf("스트라이크의 갯수 %d /  볼의 갯수 %d \n", strike,ball);
		
		//TODO 005 게임 로직 테스트
//		BaseBallGame game = new BaseBallGame();
//		game.play();
		
		//TODO 006 라이브러리 테스트
//		String answer = DefineUtil.getAnswer("게임을 다시 시작하시겠습니까? Y or N");
//		System.out.println(answer);
		
		//TODO 007 숫자야구게임 완료 테스트 시나리오 테스트
//		System.out.println("게임의 크기를 선택해 주세요");
//		int gameKind = DefineUtil.getNum();
//		BaseBallGame game = new BaseBallGame(gameKind);
//		game.newGame();
		
		//TODO 007 숫자야구게임 완료 singleton 적용
		System.out.println("게임의 크기를 선택해 주세요");
		int gameKind = DefineUtil.getNum();
		BaseBallGame game = BaseBallGame.getInstance(gameKind);
		game.newGame();
	}

}














