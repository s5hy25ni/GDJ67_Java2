package com.comeon.cardgame.function;

import java.util.Arrays;


/**
 * high 모드
 */
public class CompareHigh extends CompareImpl {
	
	private static CompareHigh compareHigh;
	
	private CompareHigh() {};
	
	public static CompareHigh getInstance() {
		if(compareHigh == null) {
			compareHigh = new CompareHigh();
		}
		return compareHigh;
	}

	/**
	 * 무늬가 같거나 숫자가 같은 카드들을 비교하여<br>
	 * 낮은 애들은 유효값에서 제거하고, 교환 추천에 추가
	 */
	@Override
	protected void isValid(int targetIdx, int compareIdx) {		
		if( nowPShape[targetIdx].equals(nowPShape[compareIdx]) ||
				nowPNumber[targetIdx].equals(nowPNumber[compareIdx])) {
			validValue.remove(currentCards[targetIdx]);
			changeIdx.add(targetIdx);
		}
	}
	
	/**
	 * 9,10,j,Q,K 등이 있으면 각 족보에 맞는 이름을 부여하고<br>
	 * 그 외에는 가장 가장 낮은 숫자를 봇 이후에 추가
	 */
	@Override
	protected String madeName() {
		if(Arrays.equals(nowPNumber, new String[] {"10","J","Q","K"})) {
			return "골프";
		} else if (Arrays.equals(nowPNumber, new String[] {"9","J","Q","K"})){
			return "세컨드";
		} else if (Arrays.equals(nowPNumber, new String[] {"9","10","Q","K"})){
			return "써드";
		} else {
			return "봇"+makeJokboNumber();
		}
	}

}
