package com.min.edu.example;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

// JSON은 키와 값으로 구성되어 있는 문자열이다
// 파일 혹은 문자열의 JSON 구조를 쉽게 선택, 탐색하기 쉽도록 JSON 객체 <=> JCF와 호환
// {"키":"값"}, {"키1":"값1", "키2":"값2", "키3":"값3" ...} => JSONObject
// {"키", [{"ID":"1"},{"ID":"2"},{"ID":"3"}]} => JSONArray
public class JSONExample {

	public static void main(String[] args) {
		
		String json = "{\"name\":\"산군\",\"address\":\"흰산\"}";
		System.out.println("String like json"+json);
		
		Gson gObj = new Gson();
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "산군");
		map.put("address", "흰산");
		
		// Map to Json
		String jsonMap = gObj.toJson(map);
		System.out.println("Map객체를 JSON 형태의 문자열로 변경 : "+  jsonMap);
		
		//Json to Map
		Map mapJson = gObj.fromJson(jsonMap, Map.class);
		System.out.println("JSON 형태의 문자열을 Map으로 변경 : " + mapJson.toString());
		
		//. --- JSON 객체를 생성하는 방법
		// Map -> library사용 -> JSON String -> Rest처리로 전달
		JsonObject obj = new JsonObject();
		obj.addProperty("name", "녹치");
		obj.addProperty("id", "nokchi");
		obj.addProperty("password", "단약");
		obj.addProperty("age", "100");
		
		System.out.println("JSONObject로 작성 : " +  obj);
		
		// String 형태의 JSON이던, json파일을 읽어오던 
		// JSON 객체의 내부값을 가져오는 방법
		// 타입에 따라서 가져와야 합니다
		JsonElement ele = obj.get("name");
		System.out.println(ele.toString());	
		System.out.println("JSON Sring :" + obj.get("name").getAsString()); 
		System.out.println("JSON Interget :" + obj.get("age").getAsInt());

		
		JsonObject book1 = new JsonObject();
		book1.addProperty("name", "자바의 정석");
		book1.addProperty("publisher", "한빛미디어");
		book1.addProperty("price", "3800");
		
		JsonObject book2 = new JsonObject();
		book2.addProperty("name", "Oracle");
		book2.addProperty("publisher", "Oracle");
		book2.addProperty("price", "2700");
		
		JsonArray bookStore = new JsonArray();
		bookStore.add(book1);
		bookStore.add(book2);
		
		JsonObject shop = new JsonObject();
		shop.add("pub", bookStore);
		
		System.out.println(shop.toString());
		JsonElement ele_no1= shop.get("pub");
		JsonArray jArr = ele_no1.getAsJsonArray();
		System.out.println(jArr.get(0).getAsJsonObject().get("name").getAsString());
		
	}
	
	
}







