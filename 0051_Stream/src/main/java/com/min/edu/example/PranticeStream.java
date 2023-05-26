package com.min.edu.example;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.min.edu.beans.Person;
import com.min.edu.mockdata.MockData;

public class PranticeStream {

	//email만 추출해서 출력 
//	@Test
	public void practice001() throws IOException {
		List<String> emails = MockData.getPeople().stream().
				map(Person :: getEmail).
//				map(t -> t.getEmail()).
				collect(Collectors.toList());
		emails.forEach(System.out :: println);
	}
	
	//car에서 10000 이하의 차가격에 1.5배의 새로운 가격을 부여
	@Test
	public void priceCar() throws IOException {
		List newList = MockData.getCar().stream().
				filter(c -> {
					System.out.println("--f--"+c);
					return c.getPrice() < 10000;
				}).
				map(c -> {
					System.out.println("--m1--"+c);
					return c.getPrice() ;
				}).
				map(price -> {
					System.out.println("--m2--"+price);
					return price + price*1.5;
				}).
				collect(Collectors.toList());
		
		for (Object o : newList) {
			System.out.println(o);
		}
	}
}




