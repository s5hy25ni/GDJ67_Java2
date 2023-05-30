package com.min.edu;

public abstract class Game {

	protected int playerCount;
	abstract void initializeGame();
	abstract void makePlay(int player); // new Player();
	abstract boolean endOfGame();
	abstract void printWinner();
	
	public final void playOneGame(int playerCount) {
		this.playerCount = playerCount;
		initializeGame();
		int j = 0; 
		while (endOfGame()) {
			makePlay(j);
			j = (j+1)%this.playerCount;
		}
		printWinner();
	}
}
