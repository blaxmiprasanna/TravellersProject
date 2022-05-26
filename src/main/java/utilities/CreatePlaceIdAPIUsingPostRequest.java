package utilities;

import io.restassured.response.Response;
import jsonRequestAndResponse.JsonPostRequest;
import utilities.HelperClass;

public class CreatePlaceIdAPIUsingPostRequest {
	
	public static Object postRequest() throws Exception {
		 
//		RestAssured.baseURI = HelperClass.readDataFromPropertyFile().getBaseURL();
		
		Response response=JsonPostRequest.createPostRequest(HelperClass.readDataFromPropertyFile().getKey(), 
				HelperClass.readDataFromPropertyFile().getContentType(), payloadFiles.PayloadToCreatePostRequest.postRequest(), 
				HelperClass.readDataFromPropertyFile().getBaseURL(),
				HelperClass.readDataFromPropertyFile().getBasePathForPost());	
		
			return response.jsonPath().get("place_id");
		
//		System.out.println(response.jsonPath().get("place_id"));
	}


}
