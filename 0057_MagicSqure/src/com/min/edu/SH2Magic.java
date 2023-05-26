package com.min.edu;

public class SH2Magic extends MagicImpl{

	public SH2Magic(int n) {
		super(n);
	}

	@Override
	public void make() {
		int n = magic.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if((i>=0 && i<(n/4) || i>=n/4*3 && i<n)) {
					if(j>=n/4 && j<n/4*3) {						
						magic[i][j] = (n-i)*n-j;
					} else {
						magic[i][j] = (i*n)+j+1;
					}
				} else {
					if(j>=0 && j<(n/4) || j>=n/4*3 && i<n) {						
						magic[i][j] = (n-i)*n-j;
					} else {
						magic[i][j] = (i*n)+j+1;
					}
				}
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
