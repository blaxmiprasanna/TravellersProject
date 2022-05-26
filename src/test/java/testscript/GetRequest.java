package testscript;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import payloadFiles.JSONGetRequest;
import utilities.HelperClass;

public class GetRequest {

	@Test
	public void getRequest() {
		
		Response response=null;
		
		try {
			Object place_id = CreatePlaceIdAPIUsingPostRequest.postRequest();
			
			 response = JSONGetRequest.getRequest(HelperClass.readDataFromPropertyFile().getKey(), 
					HelperClass.readDataFromPropertyFile().getContentType(),
					HelperClass.readDataFromPropertyFile().getBaseURL(),
					HelperClass.readDataFromPropertyFile().getBasePathForGet(),place_id);
			
			
			Assert.assertEquals(200, response.statusCode());
			Assert.assertEquals("-178.383494", response.path("location.latitude"));
			Assert.assertEquals("-216.572638", response.path("location.longitude"));
			Assert.assertEquals("73", response.path("accuracy"));
			Assert.assertEquals("Parker", response.path("name"));
			Assert.assertEquals("678 East WT Harris Road", response.path("address"));
			Assert.assertEquals("(+1) 091 8003201", response.path("phone_number"));
			Assert.assertEquals("http://rahulshettyacademy.com", response.path("website"));
			Assert.assertEquals("Virgina-IN", response.path("language"));
			response.path("types").equals("Walgreens");
			response.path("types").equals("Drugs");
			Assert.assertEquals(200, response.statusCode());
			String contentType = response.getContentType().toString();
			String [] value = contentType.split(";");
			Assert.assertEquals("application/json", value[0]);
			System.out.println(response.getTime());
			System.out.println(response.headers());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getStackTrace().toString());
		}
	}
}
