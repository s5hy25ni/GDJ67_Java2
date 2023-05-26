package com.min.edu;

import static org.junit.Assert.*;

import org.junit.Test;

public class Main_JUnit {

	@Test
	public void test() {
			TestClass t = new TestClass();
			boolean isc1 =  t.isc(2);
			assertTrue(isc1);
			boolean isc2 =  t.isc(3);
			assertFalse(isc2);
			System.out.println(isc1);
	}

}
