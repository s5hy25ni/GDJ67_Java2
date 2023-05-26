package com.min.edu;

public class OddMagic extends MagicImpl {

	public OddMagic() {
		this(3);
	}
	public OddMagic(int n) {
		super(n);
	}

	@Override
	public void make() {
		int n = magic.length; // 가로 magic[0].length, 세로 magic.length

		int x = 0;
		int y = 1;
		
		magic[x][y] = 1;
		
		for (int i = 2; i <= n*n; i++) {
			int tempX = x;
			int tempY = y;
			
			if((x-1) <0 ) {
				x = n-1;
			} else {
				x--;
			}
			
			if((y-1) < 0) {
				y = n-1;
			} else {
				y--;
			}
			
			if(magic[x][y] != 0 ) {
				x = tempX+1;
				y = tempY;
			}
			
			magic[x][y] = i;
		}
	}

//	@Override
//	public void print() {
//		for (int i = 0; i < magic.length; i++) {
//			for (int j = 0; j < magic.length; j++) {
//				System.out.printf("%d\t", magic[i][j]);
//			}
//			System.out.println();
//		}
//
//	}

}
