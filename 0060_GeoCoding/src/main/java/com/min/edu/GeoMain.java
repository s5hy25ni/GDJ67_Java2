package com.min.edu;

import java.util.Map;

public class GeoMain {

	public static void main(String[] args) {
		Map<String, String> resultMap = GeocodingUtil.getConvert("서울 금천구 가산디지털2로 95");
//		System.out.println(resultMap);
		
		String viewMap = "https://simg.pstatic.net/static.map/image?version=1.1&crs=EPSG:4326&baselayer=bl_vc_bg&exception=xml&scale=2&caller=mw_smart_booking&overlayers=ol_vc_an&center="
										+ resultMap.get("LAT")+","+resultMap.get("LNG")
										+ "1&markers=type,default2,"
										+ resultMap.get("LAT")+","+resultMap.get("LNG")
										+ "&level=10&w=340&h=150";
		
		System.out.println(viewMap);
	}

}
