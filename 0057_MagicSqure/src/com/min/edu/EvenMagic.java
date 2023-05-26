package com.min.edu;

public class EvenMagic extends MagicImpl {

	public EvenMagic(int n) {
		super(n);
	}

	@Override
	public void make() {
		int n = magic.length;
		for (int i = 0; i < n*n; i++) {
			magic[i/n][i%n] = i+1;
		}
	}

	@Override
	public void print() {
		for (int i = 0; i < magic.length; i++) {
			for (int j = 0; j < magic.length; j++) {
				System.out.printf("%d\t", magic[i][j]);
			}
			System.out.println();
		}
	}

}
