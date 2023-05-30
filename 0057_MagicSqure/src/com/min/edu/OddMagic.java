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
		int n = magic.length; //가로는 magic[0].length , 세로는 magic.length

		int x = 0;
		int y = 1;
		magic[x][y] = 1;


		for (int i = 2; i <= n*n; i++) {

			int tmpX = x;
			int tmpY = y;
			
			if(x-1 < 0) {
				x = n-1;
			}else {
				x--;
			}
			if(y-1 < 0) {
				y = n-1;
			}else {
				y--;
			}
			
			if(magic[x][y] !=0) {
				x = tmpX+1;
				y = tmpY;
			}
			
			magic[x][y] = i;
		}

	}

}
