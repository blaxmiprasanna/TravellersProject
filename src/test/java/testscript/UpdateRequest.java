package testscript;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import extendedReport.ExtentListeners;
import io.restassured.response.Response;
import jsonRequestAndResponse.JSONGetRequest;
import jsonRequestAndResponse.JsonPostRequest;
import jsonRequestAndResponse.JsonUpdateRequest;
import junit.framework.Assert;
import payloadFiles.PayloadToCreatePostRequest;
import payloadFiles.UpdatePayload;
import utilities.HelperClass;

@Listeners(extendedReport.ExtentListeners.class)

public class UpdateRequest extends ExtentListeners{

	@Test(priority=3)
	public void updateRequest(){
		Response response=null;
		try {
			
			Response postResponse=JsonPostRequest.createPostRequest(HelperClass.readDataFromPropertyFile().getKey(), 
					HelperClass.readDataFromPropertyFile().getContentType(), PayloadToCreatePostRequest.postRequest(), 
					HelperClass.readDataFromPropertyFile().getBaseURL(),
					HelperClass.readDataFromPropertyFile().getBasePathForPost());	
			
				Object place_id = postResponse.jsonPath().get("place_id");
				testReport.get().log(Status.PASS, "place id is extracted");

			 response = JsonUpdateRequest
					.jsonUpdateRequest(HelperClass.readDataFromPropertyFile().getContentType(), UpdatePayload.updatePayload(place_id),
							HelperClass.readDataFromPropertyFile().getBaseURL(),
							HelperClass.readDataFromPropertyFile().getBasePathForUpdate());
			
			testReport.get().log(Status.PASS, "updated the address");
			
			System.out.println(response.getBody().asPrettyString());
			
			Assert.assertEquals("Address successfully updated", response.path("msg"));
			testReport.get().log(Status.PASS, "address is successfully updated");
			
			Response responseForGet = JSONGetRequest.getRequest(HelperClass.readDataFromPropertyFile().getKey(),
					HelperClass.readDataFromPropertyFile().getContentType(),
					HelperClass.readDataFromPropertyFile().getBaseURL(),
					HelperClass.readDataFromPropertyFile().getBasePathForGet(),place_id);
			
			System.out.println(responseForGet.getBody().asPrettyString());
			
			System.out.println(responseForGet.path("address"));
			testReport.get().log(Status.PASS, "validated the updated address in response json");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getStackTrace());
//			ExceptionalHandlingForStatusCode.errorHandling(response.statusCode());
		}
	}
	
}
