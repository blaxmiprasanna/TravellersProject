package payloadFiles;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import utilities.HelperClass;
import utilities.ReadPayLoadData;

public class PayloadToCreatePostRequest {
	
	public static String postRequest() throws Exception {

		RestAssured.baseURI = HelperClass.readDataFromPropertyFile().getBaseURL();
		
		ReadPayLoadData readData=new ReadPayLoadData();
		
		readData.readExcelData("TestCase14");
		
		LinkedHashMap<Object, Object> map = new LinkedHashMap<Object, Object>();
		
		LinkedHashMap<Object, Object> location = new LinkedHashMap<Object, Object>();

		location.put("lat",ReadPayLoadData.pojoClass.getLatitude());

		location.put("lng", ReadPayLoadData.pojoClass.getLongitude());
		map.put("location", location);

		map.put("accuracy", ReadPayLoadData.pojoClass.getAccuracy());
		map.put("name", ReadPayLoadData.pojoClass.getName());
		map.put("phone_number", ReadPayLoadData.pojoClass.getPhone_number());
		map.put("address", ReadPayLoadData.pojoClass.getAddress());
		
		ArrayList<String> list=new ArrayList<String>();
		list.add(ReadPayLoadData.pojoClass.getType1());
		list.add(ReadPayLoadData.pojoClass.getType2());
		
		map.put("types", list);
		map.put("website", "http://rahulshettyacademy.com");
		map.put("language", ReadPayLoadData.pojoClass.getLanguage());
		
		ObjectMapper objectMapper = new ObjectMapper();

		String data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		
//		System.out.println(data);
		return data;
	}
}
