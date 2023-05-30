package com.min.edu;

import java.awt.Image;

public class MagicMain {

	public static void main(String[] args) {
//		IMagic square = new OddMagic(3);
//		square.make();
//		square.print();
		
//		IMagic square = new SHMagic(4);
//		square.make();
//		square.print();

//		IMagic square = new EvenMagic(4);
//		square.make();
//		square.print();
		
//		IMagic square = new SH2Magic(4);
//		square.make();
//		square.print();
		
//		IMagic square = new OddMagic(4);
//		square.make();
//		square.print();
		
//		MagicSquareProcess magic = MagicSquareProcess.getInstance();
//		try {
//			IMagic m = magic.factory(); // 숫자를 정상적으로 입력하면 각 객체가 반환, 유효값이 아닌 경우 null이 반환
//			if(m!=null) {
//				m.make();
//				m.print();
//			}
//		} catch (NoMasicException e) {
//			e.printStackTrace();
//		}
		
		MagicSquareProcess magic = MagicSquareProcess.getInstance();
		while (true) {
			try {
				IMagic m = magic.factory();
				DeligateMagicSquare.magicSquareLoad(m);
				break;
			} catch (NoMasicException e) {
				e.printStackTrace();
			}
		}
		
	}

}
