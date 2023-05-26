package com.min.edu;

public class SixMagicSquare extends MagicImpl {

	public SixMagicSquare() {
		this(6);
	}
	public SixMagicSquare(int n) {
		super(n);
	}

	@Override
	public void make() {
		makeA();
		makeB();
		makeC();
		makeD();
		magicDependency();
	}
	private void magicDependency() {
		int n = magic.length;
		MagicImpl odd = new OddMagic(n/2);
		odd.make();
		int[][] oddMagic = odd.getMagic();
		
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/2; j++) {
				this.magic[i][j] += oddMagic[i][j];
				this.magic[i][j+n/2] += oddMagic[i][j];
				this.magic[i+n/2][j] += oddMagic[i][j];
				this.magic[i+n/2][j+n/2] += oddMagic[i][j];
			}
		}
		
	}
	private void makeD() {
		int n = magic.length;
		int multi = n/2*n/2;
		
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/2; j++) {
				magic[i][j] *= multi; 
				magic[i][j+n/2] *= multi; 
				magic[i+n/2][j] *= multi; 
				magic[i+n/2][j+n/2] *= multi; 
			}
		}
	}
	private void makeC() {
		int n= magic.length;
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/2; j++) {
				if(magic[i][j] == 3) {
					magic[i+n/2][j] =0;
				}else {
					magic[i+n/2][j] =3;
				}
				
				if(magic[i][j+n/2] == 2) {
					magic[i+n/2][j+n/2] = 1;
				}else {
					magic[i+n/2][j+n/2] = 2;
				}
			}
		}
		
	}
	private void makeB() {
		int n = magic.length;
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/2; j++) {
				magic[i][j+n/2]  =1;
			}
		}
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/2 - (n/4-1); j++) {
				magic[i][j+n/2]  =2;
			}
		}
	}
	private void makeA() {
		int n= magic.length;
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/4; j++) {
				if(i == n/4) {
					magic[i][j+1] = 3;
				}else {
					magic[i][j] = 3;
				}
			}
		}
	}
	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

}









