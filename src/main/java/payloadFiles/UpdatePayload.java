package payloadFiles;

import java.util.LinkedHashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UpdatePayload {
	
	public static String updatePayload(Object place_id) throws Exception{
		
//		Object place_id=CreatePlaceIdAPIUsingPostRequest.postRequest();
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("place_id", place_id);
		map.put("address", "9500 Ripple way");
		map.put("key", "qaclick123");
		
		ObjectMapper objectMapper = new ObjectMapper();

		String data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
//		System.out.println(data);
		
		return data;
	}

}
