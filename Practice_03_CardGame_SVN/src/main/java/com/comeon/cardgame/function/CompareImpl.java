package com.comeon.cardgame.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.comeon.cardgame.tool.CardOne;
import com.comeon.cardgame.tool.Player;

/**
 * compare 관련 공통 메서드들은 구현하고<br>
 * 그 외에는 자식 클래스들이 구현하도록 abstract로 되어 있다.
 */
public abstract class CompareImpl implements ICompare {

	List<Player> players;
	CardOne[] currentCards;
	Player[] ranking;
	
	List<CardOne> validValue;
	Set<Integer> changeIdx;
	Map<Player, Integer> score;

	String[] nowPShape;
	String[] nowPNumber;
	
	protected CompareImpl() {
		players = Game.room.getPlayers();
		ranking  = new Player[players.size()];
		changeIdx = new HashSet<Integer>();
		nowPShape = new String[CARDNUM];
		nowPNumber = new String[CARDNUM];
	}
	
	/**
	 * 플레이어가 배분 또는 교환 받은 카드들을 받아와<br>
	 * 새로운 족보와 교환 추천을 만들어주는 전체 프로세스
	 */
	@Override
	public void makeJokboAndChange() {
		for (Player player : players) {
			if(player.getIsDie()) continue;
			clear();
			CardOne[] after = bubbleSort(player.getMyCard());
			player.setMyCard(after);
			currentCards = player.getMyCard().clone();
			separateCard();
			findValidValue();
			String jokbo = makeJokboName();
			player.setJokbo(jokbo);
			player.setChangeIdx(new ArrayList<Integer>(changeIdx));
		}
	}
	
	/**
	 * 새로 족보 또는 교환 추천을 만들때마다<br>
	 * 필요한 변수들을 초기화
	 */
	protected void clear() {
		if(validValue != null) {
			validValue.clear();
		}
		if(changeIdx != null) {
			changeIdx.clear();
		}
		if(score != null) {
			score.clear();
		}
	}
	
	/**
	 * 카드를 배분 또는 교환 받은 후<br>
	 * 숫자 순서 -> 무늬 순서대로 정렬(클-하-다-스)
	 */
	protected CardOne[] bubbleSort(CardOne[] before) {
		CardOne[] after = before.clone();
		int len = after.length;
		CardOne temp;
		
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len - 1; j++) {
				if (after[j].getSize() > after[j + 1].getSize()) {
					temp = after[j];
					after[j] = after[j + 1];
					after[j + 1] = temp;
				}
			}
		}
		
		return after;
	}
	
	/**
	 * 카드를 분리 하는 메소드<br>
	 * low랑 high는 같지만<br>
	 * reverse의 경우에는 가지고 있는 무늬의 수를 세는 기능이 추가된다.
	 */
	protected void separateCard() {
		for (int i = 0; i < currentCards.length; i++) {
			nowPShape[i] = currentCards[i].getOne().substring(0, 1);
			nowPNumber[i] = currentCards[i].getOne().substring(1);
		}
	}
	
	/**
	 * 각 카드가 족보에 해당하는지 찾기 위해 for문을 돌리는 메서드<br>
	 * low랑 high는 각 무늬와 숫자를 모두 분리해야 해서 이중for문<br>
	 * reverse는 따로 override
	 */
	protected void findValidValue() {
		validValue = new ArrayList<CardOne>(List.of(currentCards.clone()));
		int len = currentCards.length;
		
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				isValid(i, j);
			}
		}
	}
	
	/**
	 * 각 모드마다 유효 값이 달라 각자 구현<br>
	 * 각 카드가 유효값에 해당되는지 확인
	 */
	protected abstract void isValid(int targetIdx, int compareIdx);
	
	/**
	 * 모든 플레이어의 카드덱을 가져와 높은 순서대로 정렬<br>
	 * 1등이 유저가 아닌 경우 유저가 패배
	 */
	@Override
	public void comparePlayers() {
		saveScore();
		for (int i = 0; i < ranking.length; i++) {
			ranking[i] = players.get(i);
		}
		bubbleSortRanking();
		if(ranking[0].getIsCom()) {
			Game.room.setIsUserLose(true);
		}
	}

	/**
	 * 카드가 아닌 유저의 족보를 비교하여 순위대로 정령<br>
	 * high랑 reverse는 총 합이 클수록 높은 순위<br>
	 * low는 낮을수록 높은 순위라 따로 구현
	 */
	protected void bubbleSortRanking() {
		Player temp;
		for (int i = 0; i < ranking.length; i++) {
			for (int j = 0; j < ranking.length - 1; j++) {
				if (score.get(ranking[j]) < score.get(ranking[j+1])) {
					temp = ranking[j];
					ranking[j] = ranking[j +1];
					ranking[j+1] = temp;
				}
			}
		}
		Game.room.setRanking(ranking);
	}

	/**
	 * 각 플레이어의 점수를 Map에 넣는다<br>
	 * 비교시 각 플레이어가 key가 되어 score(value)를 찾아간다.
	 */
	protected void saveScore() {
		score = new HashMap<>();
		for (Player player : players) {
			score.put(player, calScore(player));
		}
	}
	
	/**
	 * 족보의 이름과 숫자(탑~)을 가져와 점수를 매긴다.<br>
	 * 족보 이름의 길이가 다 다르므로 화이트스페이스의 idx를 찾아 구분한다.
	 */
	protected int calScore(Player player) {
		String jokbo = player.getJokbo();
		int idx = jokbo.indexOf("\u0020");
		return compareJokboName(jokbo.substring(0,idx))+compareJokboTop(jokbo.substring(idx+1));
	}
	
	/**
	 * 족보의 이름을 구분하여 점수를 부여한다.<br>
	 * high랑 reverse의 경우 높은 족보일수록 높은 점수를 부여한다.<br>
	 * low의 경우 높은 족보일수록 낮은 점수를 부여하기 때문에 따로 override
	 */
	protected int compareJokboName(String jokboName) {
		int score = switch (jokboName) {
		case "메이드" ->60;
		case "베이스" ->40;
		case "투베이스" ->20;
		default ->0;
		};
		return score;
	}
	
	/**
	 * 족보의 top에 점수를 부여한다<br>
	 * high와 reverse는 높은 숫자일수록 높은 점수가 부연된다<br>
	 * low는 따로 오버라이드<br>
	 * 숫자가 아닌 족보 top들(퍼펙트, 골프, 세컨드, 써드)외의<br>
	 * 숫자 족보들(탑 A,J,3,4 등)은 따로 메소드를 호출한다.<br>
	 */
	protected int compareJokboTop(String jokboTop) {
		int score = switch (jokboTop) {
		case "퍼펙트"->9;
		case "골프" ->8;
		case "세컨드" ->7;
		case "써드" -> 6;
		default ->compareJokboTopNumber(jokboTop.substring(1));
		};

		return score;
	}

	/**
	 * 각 숫자에 해당하는 인덱스로 점수를 부여한다.<br>
	 * 모든 모드가 똑같음
	 */
	protected int compareJokboTopNumber(String jokboTopNumber) {
		int score = 0;
		for (int i = 0; i < CardOne.number.length; i++) {
			if(jokboTopNumber.equals(CardOne.number[i])) {
				score = i;
			}
		}
		return score;
	}

	/**
	 * 유효 카드의 수에 따라 족보의 이름을 부여한다<br>
	 * 유효 카드가 4장 -> 메이드<br>
	 * 유효 카드가 3장 -> 베이스<br>
	 * 유효 카드가 2장 -> 투베이스<br>
	 * 유효 카드가 1장 -> 논<br>
	 * high, reverse는 탑이 아닌 봇이기 때문에 같이 구현<br>
	 */
	protected String makeJokboName() {
		String jokbo = switch (validValue.size()) {
		case 4 ->"메이드\u0020"+madeName();
		case 3->"베이스\u0020봇"+makeJokboNumber();		
		case 2->"투베이스\u0020봇"+makeJokboNumber();
		default->"논\u0020봇"+makeJokboNumber();
		};
		return jokbo;
	}
	
	/**
	 * 메이드는 탑+숫자 외에 가장 최상위 족보들이<br>
	 * 퍼펙트, 골프, 세컨드, 써드 등으로 따로 이름이 있어<br>
	 * 따로 이름을 부여한다.
	 */
	protected abstract String madeName();
	
	/**
	 * 탑 이후 숫자를 가져온다<br>
	 * high랑 reverse 동일
	 */
	protected String makeJokboNumber() {
		if(!validValue.isEmpty()) {
			return validValue.get(0).getOne().substring(1);
		} else {			
			return "";
		}
	}


}
