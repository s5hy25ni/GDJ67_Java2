package com.min.edu.play;

/**
 * 생성된 pitcher와  hitter를 사용하여 strike과 ball을 판단하는 bean 클래스
 * @author Jeon Min Kyun
 */
public class Umpire {
	private int[] pBall;
	private int[] hBall;
//	private int boxLength;
	
//	public Umpire() {
//		this(3);
//	}

//	public Umpire(int n) {
//		this.boxLength = n;
//		pBall = new int[n];
//		hBall = new int[n];
//	}

//	public Umpire(int[] pBall, int[] hBall) {
//		super();
//		this.pBall = pBall;
//		this.hBall = hBall;
//	}

	public void setpBall(int[] pBall) {
		this.pBall = pBall;
	}

	public void sethBall(int[] hBall) {
		this.hBall = hBall;
	}
	/**
	 * 멤버필드의  pBall과 hBall을 판단하여 같은 index와 같은 값을 판단하여  cnt를 반환한다
	 */
	public int getStrike() {
		int cnt = 0;
		for (int i = 0; i < hBall.length; i++) {
			if(pBall[i] == hBall[i]) {
				cnt++;
			}
		}
		return cnt;
	}
	/**
	 * 멤버필드의  pBall과 hBall을 판단하여 같은 index는 다르지만 같은 값 값을 판단하여  cnt를 반환한다
	 */
	public int getBall() {
		int cnt = 0;
		for (int i = 0; i < pBall.length; i++) {
			for (int j = 0; j < hBall.length; j++) {
				if(i != j && pBall[i] == hBall[j]) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	
}










