package com.comeon.cardgame.function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.comeon.cardgame.tool.Player;

/**
 * 베팅 타입 경우의 수, 콜 금액 등을 판단 및 계산하는 클래스
 * */
public class Betting {
	
	private static Betting betting;
	private List<Player> players;
	private int turn;	
	private Player currentPlayer;
	private int currentBetMoney;
	private Map<Player, Integer> playerTurnPay;

	private int raiseCnt;
	private int callCnt;
	
	private Betting() {};
	
	public static Betting getInstance() {
		if(betting==null) {
			betting = new Betting();
		}
		return betting;
	}
	
	/**
	 * 첫 입장시 앤티를 지불한다.
	 */
	public void payAnte() {
		System.out.println();
		System.out.println(" ▷ 보스부터 앤티(￦100,000)를 지불합니다.");
		System.out.println();
		players = Game.room.getPlayers();
		turn = Game.room.getBoss();
		for (int i = 0; i < players.size(); i++) {
			Game.room.setSeed(Game.room.getSeed()+100000);
			currentPlayer = players.get(turn++%Game.room.getPlayers().size());
			currentPlayer.setMoney(currentPlayer.getMoney()-100000);
			currentPlayer.setTotalBetting(100000);
			System.out.printf(" ▷ %s 님의 현재 잔액은 ￦%s 입니다.\n",currentPlayer.getName(), Game.w.format(currentPlayer.getMoney()));
		}
		System.out.println();
	}
	
	/**
	 * 모든 플레이어의 베팅을 차례대로 돌린다.<br>
	 * 현재 플레이어가 die인 상태이면 다음 차례로 넘어간다. <br>
	 * 유저가 die인 상태이거나 유저를 제외한 모든 플레이어가 die인 상태이면 게임을 종료한다.<br>
	 * 모든 플레이어가 call하면 베팅을 종료한다.
	 */
	public void bettingProcess() {
		System.out.println(" ▷ 베팅을 시작합니다.");
		System.out.println();
		playerPayClear();
		while (true) {
			currentPlayer = players.get(turn++%Game.room.getPlayers().size()); 
			if(!currentPlayer.getIsDie()) {
				personalBettingProcess();				
			}
			if(!currentPlayer.getIsCom() && currentPlayer.getIsDie()) {
				Game.room.setIsUserLose(true);
				break;
			} else if (Game.room.getDieCnt()==players.size()-1 ) {
				Game.room.setIsGameEnd(true);		
				break;
			} else if (callCnt==players.size()-1) {
				break;
			}
		}
		System.out.println(" ▷ 베팅이 종료되었습니다.");
		System.out.println();
	}
	
	/**
	 * 총 4번의 베팅이 각각 끝날 때마다 이전 베팅 정보를 초기화한다.
	 */
	private void playerPayClear() {
		turn = Game.room.getBoss();
		currentBetMoney = 0;
		raiseCnt = 0;
		callCnt = 0;
		playerTurnPay = new HashMap<Player, Integer>();
		for (Player player : players) {
			playerTurnPay.put(player, 0);
		}
	}
	
	/**
	 * 개인 베팅 프로세스<br>
	 * 베팅 경우의 수 구하기 -> 경우의 수 중에서 베팅 선택하기 -> 베팅 금액 계산하기
	 */
	private void personalBettingProcess() {	
		System.out.printf(" ▷ 현재 차례는 %s 님 입니다.\n", currentPlayer.getName());
	    List<Integer> turnType = turnType();
	    int choice = choiceBetting(turnType);
	    int betMoney = choiceType(choice);
	    if(betMoney != 0) calBetting(betMoney);
	    System.out.println();
	}
	
	/**
	 * 베팅의 경우의 수를 구하는 메서드<br>
	 * 플레이어가 선(boss)일 때는 다이, 하프, 쿼터만 선택 가능<br>
	 * 각 베팅에서 레이즈가 3회까지 가능하다. (다이, 콜만 선택 가능)<br>
	 * 레이즈가 3번 이루어지기 전에는 모든 베팅 타입이 가능하다. (다이, 하프, 쿼터, 콜, 따당)
	 */
	private List<Integer> turnType() {
		if(raiseCnt==3) {
			return new ArrayList<Integer>(List.of(1,4));	// 다이, 콜만 가능
		}
		else if(raiseCnt>=1&&raiseCnt<3) {	 
			return new ArrayList<Integer>(List.of(1,2,3,4,5));	// 모두 가능
		}
		else { // boss 일 때
			return new ArrayList<Integer>(List.of(1,2,3));	// 다이, 하프, 쿼터만 가능
		}
	}
	
	/**
	 * 베팅 가능한 경우의 수 중에서<br>
	 * 현재 플레이어가 컴퓨터일 경우 자동 선택<br>
	 * 현재 플레이어가 유저일 경우 유저가 입력
	 */
	private int choiceBetting(List<Integer> turnType) {
		int n = 0;

		if (currentPlayer.getIsCom()) {
			n =  computerBetting(turnType);
		} else {
			n = userBetting(turnType);
		}
		return n;

	}
	
	/**
	 * 컴퓨터의 족보가 메이드이거나 베이스이면 다이는 선택하지 못한다.<br>
	 * 그 외에는 경우의 내에서 선택한다.
	 */
	private int computerBetting(List<Integer> turnType) {
		int n = 1;
		if(currentPlayer.getJokbo().contains("메이드") || currentPlayer.getJokbo().contains("베이스")) {
			n = turnType.get((int)(Math.random()*(turnType.size()-1))+1);			
		} else {
			n = turnType.get((int)(Math.random()*(turnType.size()-1)));
		}
		System.out.printf(" ▷ %s 님이 %s를 선택하셨습니다.\n",currentPlayer.getName(), bettingType(n));
		return n;
	}
	
	/**
	 * 유저가 베팅 가능한 경우의 수만 프린트하고<br>
	 * 그 외의 베팅 버튼을 입력시 재입력 받는다.
	 */
	private int userBetting(List<Integer> turnType) {
		System.out.printf(" ▷ %s 님이 베팅하실 차례입니다.\n", currentPlayer.getName());
		System.out.printf(" ▷ 현재 가능한 베팅 타입 : ");
		for (Integer type : turnType) {;
			System.out.printf("[%d] %s(",type, bettingType(type));
			System.out.printf("￦%s)   ", Game.w.format(branch(type)));
		}
		System.out.println();
		System.out.println();
		int choice = Input.getNum(" ▶ 베팅 타입을 선택해주세요 : ", turnType); 
		System.out.println();
		System.out.printf(" ▷ %s 님이 %s를 선택하셨습니다.\n",currentPlayer.getName(),bettingType(choice));
		return choice;
	}
	
	/**
	 * 플레이어의 선택 타입을 String으로 변경
	 */
	private String bettingType(int type) {
		String sType = switch (type) {
		case 1 ->"다이";
		case 2->"하프";
		case 3->"쿼터";
		case 4->"콜";	
		default ->"따당";
		};
		return sType;
	}

	/**
	 * 플레이어가 선택한 베팅 타입에 맞게 베팅 금액을 계산하는 메서드로 분기한다.<br>
	 * 플레이어가 다이를 선택하면 ROOM에서 퇴장한다<br>
	 * 레이즈를 하면 raiseCnt를 올리고 callCnt는 0으로 초기화한다<br>
	 * 콜을 하면 callCnt를 올린다.
	 */
	private int choiceType(int choice) {	
		int betMoney;
		switch (choice) {
		case 1 -> {
			currentPlayer.setIsDie(true);
			Game.room.setDieCnt(Game.room.getDieCnt()+1);
			System.out.printf(" ▷ %s 님이 퇴장하셨습니다. (남은 플레이어 수 : %d 명)\n", currentPlayer.getName(), Game.room.getPlayers().size()-Game.room.getDieCnt());
			betMoney = branch(1);
		}
		case 2 -> {
			raiseCnt++;
			callCnt=0;
			betMoney = branch(2);
		}
		case 3 -> {
			raiseCnt++;
			callCnt=0;
			betMoney = branch(3);
		}
		case 4 -> {
			callCnt++;
			betMoney = branch(4);
		}
		default -> {
			raiseCnt++;
			callCnt=0;
			betMoney = branch(5);
		}
		};
		return betMoney;
	}
	
	/**
	 * 플레이어가 선택한 베팅에 맞는 금액을 return한다.<br>
	 * 유저 베팅 선택 타입에 예상 금액 노출시 cnt를 올리지 않기 위해 따로 분리
	 */
	private int branch(int choice) {
		int betMoney = switch (choice) {
		case 1 -> dieBetting();
		case 2 -> harfBetting();
		case 3 -> quarterBetting();
		case 4 -> callBetting();
		default ->ddadangBetting();
		};
		return betMoney;
	}
	
	/**
	 * 선택된 베팅 타입에 맞는 금액을 받아 현재 베팅 금액을 바꾸고<br>
	 * (다이의 경우에는 모두 바꾸지 않고, 콜인 경우에는 현재 배팅 금액만 바꾸지 않는다)<br>
	 * 플레이어의 보유 금액 및 누적금액, ROOM 시드 등에 반영한다.
	 */
	private void calBetting(int betMoney) {
		if(betMoney!=0) {
			if(callCnt == 0) currentBetMoney = betMoney;
			currentPlayer.setMoney(currentPlayer.getMoney()-betMoney);
			Game.room.setSeed(Game.room.getSeed()+betMoney);
			playerTurnPay.put(currentPlayer, betMoney);
			currentPlayer.setTotalBetting(betMoney);
		}
		System.out.printf(" ▷ %s 님 베팅 금액 : ￦%s, 현재 잔액 : ￦ %s, ROOM 시드 : ￦ %s\n", 
													currentPlayer.getName(), Game.w.format(betMoney), Game.w.format(currentPlayer.getMoney()), Game.w.format(Game.room.getSeed()));
		System.out.printf(" ▷ 남은 레이즈 횟수 : %d 회", 3-raiseCnt);
		System.out.println();
	}
	
	private int harfBetting(){	
		return currentBetMoney + ((Game.room.getSeed() + currentBetMoney)/2);
	}

	private int quarterBetting(){	
		return currentBetMoney + ((Game.room.getSeed() + currentBetMoney)/4);
	}

	private int callBetting() {	
		return currentBetMoney - playerTurnPay.get(currentPlayer);
	}


	private int ddadangBetting() {	
		return currentBetMoney + (currentBetMoney*2);
	}

	private int dieBetting() {
		return 0;
	}
}

