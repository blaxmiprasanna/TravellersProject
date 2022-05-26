package testscript;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import extendedReport.ExtentListeners;
import io.restassured.response.Response;
import payloadFiles.JSONGetRequest;
import utilities.HelperClass;

@Listeners(extendedReport.ExtentListeners.class)

public class GetRequest extends ExtentListeners {
	
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
			testReport.get().log(Status.PASS, "The statuscode is validated");
			Assert.assertEquals("-178.383494", response.path("location.latitude"));
			testReport.get().log(Status.PASS, "The text location.latitude is validated in the response json");
			Assert.assertEquals("-216.572638", response.path("location.longitude"));
			testReport.get().log(Status.PASS, "The text location.longitude is validated in the response json");
			Assert.assertEquals("73", response.path("accuracy"));
			testReport.get().log(Status.PASS, "The text accuracy is validated in the response json");
			Assert.assertEquals("Parker", response.path("name"));
			testReport.get().log(Status.PASS, "The text name is validated in the response json");
			Assert.assertEquals("678 East WT Harris Road", response.path("address"));
			testReport.get().log(Status.PASS, "The text address is validated in the response json");
			Assert.assertEquals("(+1) 091 8003201", response.path("phone_number"));
			testReport.get().log(Status.PASS, "The text phone_number is validated in the response json");
			Assert.assertEquals("http://rahulshettyacademy.com", response.path("website"));
			testReport.get().log(Status.PASS, "The text website is validated in the response json");
			Assert.assertEquals("Virgina-IN", response.path("language"));
			testReport.get().log(Status.PASS, "The text language is validated in the response json");
			response.path("types").equals("Walgreens");
			response.path("types").equals("Drugs");
			testReport.get().log(Status.PASS, "The text type1 and type2 are validated in the response json");
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
