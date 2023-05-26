package com.comeon.cardgame.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.comeon.cardgame.tool.CardOne;

public class CompareReverse extends CompareImpl {
	
	private static CompareReverse compareReverse;
	private Map<String, Integer> shapeCnt;
	private String validShape;
	
	private CompareReverse() {};
	
	public static CompareReverse getInstance() {
		if(compareReverse == null) {
			compareReverse = new CompareReverse();
		}
		return compareReverse;
	}

	/**
	 * reverse는 무늬가 다 다르고 숫자가 다 같으면 최상위(퍼펙트)이고<br>
	 * 그 외에는 무늬가 다 같고 숫자가 높을수록 최상위여서<br>
	 * 카드를 분리할 때 보유하는 무늬의 숫자를 따로 센다.
	 */
	@Override
	protected void separateCard() {
		for (int i = 0; i < currentCards.length; i++) {
			nowPShape[i] = currentCards[i].getOne().substring(0, 1);
			nowPNumber[i] = currentCards[i].getOne().substring(1);
		}
		countShape();
		findValidShape();
	}
	
	/**
	 * 모든 카드의 유효값을 저장하는 메소드
	 */
	@Override
	protected void findValidValue() {
		validValue = new ArrayList<CardOne>(List.of(currentCards.clone()));
		int len = currentCards.length;
		
		if(!sameNumber()) {
			for (int i = 0; i < len; i++) {
				isValid(i, i+1);
			}
		}		
	}
	
	/**
	 * 각 카드의 유효하지 않은 카드는 제거하고<br>
	 * 교환이 필요한 카드는 추가하는 메소드
	 */
	@Override
	protected void isValid(int targetIdx, int compareIdx) {	
		if( !nowPShape[targetIdx].equals(validShape) ) {
			validValue.remove(currentCards[targetIdx]);
			changeIdx.add(targetIdx);
		}
	}
	
	/**
	 * 무늬가 다르고 모두 같은 숫자이면 퍼펙트<br>
	 * 그 외에는 무늬가 같으면서 높은 숫자일수록 높은 족보가 부여된다.
	 */
	@Override
	protected String madeName() {
		if(Arrays.equals(nowPNumber, new String[] {"10","J","Q","K"})) {
			return "골프";
		} else if (Arrays.equals(nowPNumber, new String[] {"9","J","Q","K"})){
			return "세컨드";
		} else if (Arrays.equals(nowPNumber, new String[] {"9","10","Q","K"})){
			return "써드";
		} else if(sameNumber()) {
			return "퍼펙트";
		} else {
			return "봇"+makeJokboNumber();
		}
	}
	
	/**
	 * 같은 무늬 카운트를 올리는 메서드
	 */
	@SuppressWarnings("serial")
	private void countShape() {
		shapeCnt = new HashMap<String, Integer>(){{
			put("♣",0);
			put("♥",0);
			put("◆",0);
			put("♠",0);			
		}};
		for (int i = 0; i < nowPShape.length; i++) {
			shapeCnt.put(nowPShape[i], (shapeCnt.get(nowPShape[i])+1));
		}
	}

	/**
	 * 가장 많이 가지고 있는 무늬와 같은 무늬인 카드들이<br>
	 * 유효값에 저장된다.
	 */
	private void findValidShape() {
		if(!shapeCnt.containsValue(2) && !shapeCnt.containsValue(3) && !shapeCnt.containsValue(4)) {
			validShape = nowPShape[3];
		} else {
			for (int i = 0; i < nowPShape.length; i++) {
				if(shapeCnt.get(nowPShape[i]) == ICompare.CARDNUM 
						|| shapeCnt.get(nowPShape[i]) == 3) {
					validShape = nowPShape[i];
					break;
				} else if(shapeCnt.get(nowPShape[i]) == 2) {
					validShape = nowPShape[i];				
				}
			}
		}
	}
	
	private boolean sameNumber() {
		int cnt = 0;
		for (int i = 0; i < nowPNumber.length; i++) {
			if(nowPNumber[0].equals(nowPNumber[i])) {
				cnt++;
			}
		}
		return (cnt==4)?true:false;
	}
}
