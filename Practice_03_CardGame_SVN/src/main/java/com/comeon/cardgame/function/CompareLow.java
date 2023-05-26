package com.comeon.cardgame.function;

import java.util.Arrays;
import com.comeon.cardgame.tool.Player;

public class CompareLow extends CompareImpl{
	
	private static CompareLow compareLow;
	
	private CompareLow() {};
	
	public static CompareLow getInstance() {
		if(compareLow == null) {
			compareLow = new CompareLow();
		}
		return compareLow;
	}
	
	/**
	 * 무늬와 숫자가 같은 카드들을 비교하여<br>
	 * 높은 카드들을 유효카드에서 우선적으로 제거하고<br>
	 * 교환 추천에 넣는다.
	 */
	@Override
	protected void isValid(int targetIdx, int compareIdx) {		
		if( nowPShape[targetIdx].equals(nowPShape[compareIdx]) ||
				nowPNumber[targetIdx].equals(nowPNumber[compareIdx])) {
			validValue.remove(currentCards[compareIdx]);
			changeIdx.add(compareIdx);
		}
	}

	/**
	 * 점수가 낮은 순서대로 높은 랭킹에 올린다.
	 */
	@Override
	protected void bubbleSortRanking() {
		Player temp;
		for (int i = 0; i < ranking.length; i++) {
			for (int j = 0; j < ranking.length - 1; j++) {
				if (score.get(ranking[j]) > score.get(ranking[j+1])) {
					temp = ranking[j];
					ranking[j] = ranking[j +1];
					ranking[j+1] = temp;
				}
			}
		}
		Game.room.setRanking(ranking);
	}

	/**
	 * 높은 족보일수록 낮은 점수를 부여한다.
	 */
	@Override
	protected int compareJokboName(String jokboName) {
		int score = switch (jokboName) {
		case "메이드" ->0;
		case "베이스" ->20;
		case "투베이스" ->40;
		default ->60;
		};
		return score;
	}

	/**
	 * 메이드 중에서 높은 메이드일수록 낮은 점수를 부여한다.
	 */
	@Override
	protected int compareJokboTop(String jokboTop) {
		int score = switch (jokboTop) {
		case "골프" ->0;
		case "세컨드" ->1;
		case "써드" -> 2;
		default ->compareJokboTopNumber(jokboTop.substring(1));
		};

		return score;
	}
	
	/**
	 * low는 플레이어의 유효 카드들 중 높은 숫자인 애들을 비교하여<br>
	 * 가장 낮은 사람이 승리하므로<br>
	 * 봇이 아닌 탑을 사용한다.
	 */
	@Override
	protected String makeJokboName() {
		String jokbo = switch (validValue.size()) {
		case 1 ->"논\u0020탑"+makeJokboNumber();
		case 2->"투베이스\u0020탑"+makeJokboNumber();
		case 3->"베이스\u0020탑"+makeJokboNumber();		
		default ->"메이드\u0020"+madeName();
		};
		return jokbo;
	}
	
	/**
	 * low는 낮은 숫자로 메이드 중 최상위 족보가 결정된다.
	 */
	@Override
	protected String madeName() {	
		if(Arrays.equals(nowPNumber, new String[] {"A","2","3","4"})) {
			return "골프";
		} else if (Arrays.equals(nowPNumber, new String[] {"A","2","3","5"})){
			return "세컨드";
		} else if (Arrays.equals(nowPNumber, new String[] {"A","2","4","5"})){
			return "써드";
		} else {
			return "탑"+makeJokboNumber();
		}
	}
	
	/**
	 * low는 낮은 숫자가 유효카드들 중 높은 숫자를 가진 애들이<br>
	 * top 뒤에 온다.
	 */
	@Override
	protected String makeJokboNumber() {
		return validValue.get(validValue.size()-1).getOne().substring(1);
	}
}
