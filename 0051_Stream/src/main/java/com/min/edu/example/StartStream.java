package com.min.edu.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.min.edu.beans.Person;
import com.min.edu.mockdata.MockData;

public class StartStream {

//	@Test
	@DisplayName("예전 처리 방식을 테스트 합니다")
	public void oldArroch() throws IOException {
		//1. 사람들 중에서(people.json)에서 검색 
		//2. 나이는 18세이하, 10명만
		List<Person> people = MockData.getPeople(); // people.json에서 java객체로 변환
		System.out.println(people);
		List<Person> choicePeople =  new ArrayList<Person>();
		
		int limit =10;
		int cnt = 0;
		
		for (Person person : people) {
			if (person.getAge() < 18) {
				choicePeople.add(person);
				cnt++;
				if(cnt == limit) {
					break;
				}
			}
		} //for
		
//		choicePeople.forEach(System.out::println);
	} //oldArroch
	
	
//	@Test
	public void newApprochStream() throws IOException {
		List<Person> people = MockData.getPeople();
		List<Person> choicePeople = people.stream(). // 1. people의 리스트를 stream으로 만든다
				filter(p->p.getAge()<=18). // 2. 중간 판단 연산자를 작성한다
				limit(10). // 3. 중간 갯수제한을 작성한다
				collect(Collectors.toList());
		choicePeople.forEach(System.out::println);
	}
	
	
	// List에 문자열에 중에서 한개 같은 문자열을 찾아서 만들고 싶어
	public List<String> find(List<String> member, String str){
		List<String> result = new ArrayList<String>();
		for (String s: member) {
			if(s.equals(str)) {
				result.add(s);
			}
		}
		return result;
	}
	
	
	public List<String> findStream(List<String> member, String str){
		return member.stream().filter(mem -> mem.equals(str)).collect(Collectors.toList());
				
	}
	
	
}



