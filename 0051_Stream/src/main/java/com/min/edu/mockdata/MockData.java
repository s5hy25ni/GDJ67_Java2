package com.min.edu.mockdata;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.min.edu.beans.Car;
import com.min.edu.beans.Person;

/*
 * src/test/resources에 있는 json파일(Car.json, Person.json)파일을 
 * JSON 객체로 만들어 줌
 * JSON -> JAVA 객체로 만들어서 사용
 */
public class MockData {

	@SuppressWarnings("serial")
	public static List<Person> getPeople() throws IOException{
		// json파일을 읽어서 01010..데이터로 읽어 메모리에 할당함
		InputStream inputStream = Resources.getResource("people.json").openStream();
		String json = IOUtils.toString(inputStream,StandardCharsets.UTF_8);
		Type listsType = new TypeToken<ArrayList<Person>>() {}.getType();
		return new Gson().fromJson(json, listsType);
	}
	
	@SuppressWarnings("serial")
	public static List<Car> getCar() throws IOException{
		// json파일을 읽어서 01010..데이터로 읽어 메모리에 할당함
		InputStream inputStream = Resources.getResource("cars.json").openStream();
		String json = IOUtils.toString(inputStream,StandardCharsets.UTF_8);
		Type listsType = new TypeToken<ArrayList<Car>>() {}.getType();
		return new Gson().fromJson(json, listsType);
	}
	
}














