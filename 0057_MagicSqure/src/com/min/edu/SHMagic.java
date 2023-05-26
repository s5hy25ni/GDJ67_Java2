package com.min.edu;

public class SHMagic extends MagicImpl {

	public SHMagic(int n) {
		super(n);
	}

	@Override
	public void make() {
		for (int i = 0; i < magic.length; i++) {
			for (int j = (i*magic.length)+1; j < ((i+1)*magic.length)+1; j++) {
				magic[i][(j-1)%magic.length] = j;
			}
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
