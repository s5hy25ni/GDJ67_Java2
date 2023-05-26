package com.min.edu.user;

import java.util.Arrays;

public abstract class GamerImpl implements IGamer {

	protected int[] box;
	
	public GamerImpl(int n) {
		box = new int[n];
		Arrays.fill(box, -1);
	}
	
	@Override
	public abstract void make();


	public int[] getBox() {
		return box;
	}
	
	@Override
	public String toString() {
		String result = "[";
		int n =  box.length;
		for (int i = 0; i < n-1; i++) {
			result += String.format("%s,", box[i]);
		}
		result += String.format("%s]", box[n -1]);
		return result;
	}


}
