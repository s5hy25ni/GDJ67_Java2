package com.min.edu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class MagicImpl implements IMagic {

	protected int[][] magic;
	
	
	public MagicImpl(int n) {
		
		magic = new int[n][n];
	}
	
	
	
	@Override
	public abstract void make();
	
	@Override
	public void print() {
		
		int n = magic.length;
		String result = isMagicSquareCheck() ? "성공" : "실패";
		System.out.println("마방진은 " + result);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("\t%d" , magic[i][j]);
			}
			System.out.printf("=>\t%d\n" , sumRow(i));
		}
		System.out.println();
		System.out.printf("%d\t" , reverseDiagnal());
		for (int i = 0; i < n; i++) {
			System.out.printf("%d\t" , sumCol(i));
		}
		
		System.out.print(diagnal());
		System.out.println();
		
	}


	public int[][] getMagic() {
		return magic;
	}
	
	/**
	 * 
	 * @return true면 마방진 성공!!
	 */
	private boolean isMagicSquareCheck() {
		int n = magic.length;
		int[] checked = new int[n*2+2];
		
		for (int i = 0; i < n; i++) {
			//row
			checked[i] = sumRow(i);
			//col
			checked[i+n] = sumCol(i);
		}
		
		checked[2*n] = diagnal();
		checked[2*n+1] = reverseDiagnal();
		Set<Integer> checkSet = new HashSet<Integer>();
		for (int i = 0; i < checked.length; i++) {
			checkSet.add(checked[i]);
		}
	
		return checkSet.size() == 1? true : false;
	}
	
	
	
	//가로
	private int sumRow(int row) {	//magic[0][i]
		int sum = 0;
		for (int i = 0; i < magic.length; i++) {
			sum += magic[row][i];
		}
		return sum;
	}
	
	
	//세로
	private int sumCol(int col) {	//magic[i][0]
		int sum = 0;
		for (int i = 0; i < magic.length; i++) {
			sum += magic[i][col];
		}
		return sum;
	}
	
	
	//대각선
	public int diagnal() {
		int sum = 0;
		for (int i = 0; i < magic.length; i++) {
			sum += magic[i][i];
		}
		return sum;
	
	}
	
	
	//역대각선
	public int reverseDiagnal() {
		int sum = 0;
		for (int i = 0; i < magic.length; i++) {
			sum += magic[i][magic.length-1-i];
		}
		return sum;
	}
	
}
