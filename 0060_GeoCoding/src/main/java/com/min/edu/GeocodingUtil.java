package com.min.edu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 등록/입력시 주소에서 위도와 경도를 변경해주는 class
 */
public class GeocodingUtil {

	public static Map<String, String> getConvert(String address){
		Map<String, String> map = new HashMap<String, String>();
		
		String url = "http://api.vworld.kr/req/address?service=address&request=getcoord&version=2.0&crs=epsg:4326&address="
				+ address
				+ "&refine=true&simple=false&format=xml&type=road&key=4878C0A4-24C8-3AB0-957B-6A0D5A87606D";
		
		try {
			// Document = html의 내용을 가지고 있음, Jsoup은 html을 요청
			Document doc = Jsoup.connect(url).timeout(5000).get();
//			System.out.println("요청한 주소의 내용 : \n"+doc);
			Elements eleX = doc.select("x");
			Elements eleY = doc.select("y");
//			System.out.println(eleX.text().strip());
//			System.out.println(eleY.text().strip());
			map.put("LAT", eleX.text().strip());
			map.put("LNG", eleY.text().strip());
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("변경된 주소 좌표값 : "+map);
		
		return map;
		
	}
}
