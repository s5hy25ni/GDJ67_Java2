package com.comeon.cardgame.badugi;

import com.comeon.cardgame.function.Game;

/**
 * 바둑이 게임 메인 메소드
 * @author 따라와
 *
 */
public class BadugiMain {

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
		game.play();
	}

}
