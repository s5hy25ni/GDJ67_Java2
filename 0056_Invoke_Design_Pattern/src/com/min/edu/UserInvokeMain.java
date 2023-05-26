package com.min.edu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserInvokeMain {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 기존 방식의 메소드 호출
//		Other o = new Other();
//		o.a(null);
//		o.b(null);
//		o.c(null);
		
		// 실행시킬 메소드 명
		String methodName = "a";

		// Other 클래스에 포함된 메소드 중 "a" 메소드명을 가지고 있으면서, arguments가 String인 것을 호출
		Method fn = Other.class.getMethod(methodName, String.class);
		fn.invoke(new Other(), "네~ "+methodName+"입니당");
		
	}

}
