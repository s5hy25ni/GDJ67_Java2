package com.min.edu;

public class EvenMagic extends MagicImpl {

	public EvenMagic(int n) {
		super(n);
	}
	
	public void make() {
		makeSqueance();
		makeChange();
	}

	private void makeChange() {
		int n = magic.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				if(i>=0 && i<n/4 || i>=n/4*3 && i<n) {
					if(j>=n/4&& j<n/4*3) {
						magic[i][j]=n*n-(i*n+j);
					}
					
				}else {
					if(j>=0 && j<n/4 || j>=n/4*3 && j<n) {
						magic[i][j]=n*n-(i*n+j);
					}
				
				}
			}
		}
		
	}

	
	
	private void makeSqueance() {
		int n = magic.length;
		for (int i = 0; i < n*n; i++) {
			magic[i/n][i%n]=i+1;
		}		
	}

}
