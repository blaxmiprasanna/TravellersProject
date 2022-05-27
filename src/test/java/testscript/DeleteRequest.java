package testscript;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import extendedReport.ExtentListeners;
import io.restassured.response.Response;
import jsonRequestAndResponse.JSONGetRequest;
import jsonRequestAndResponse.JsonDeleteRequest;
import jsonRequestAndResponse.JsonPostRequest;
import payloadFiles.DeletePayload;
import payloadFiles.PayloadToCreatePostRequest;
import utilities.HelperClass;

@Listeners(extendedReport.ExtentListeners.class)

public class DeleteRequest extends ExtentListeners{
	
	@Test(priority=4)
	public void deleteRequest() throws Exception{
		Response responseForDelete=null;
		try {
			Response postResponse = JsonPostRequest.createPostRequest(HelperClass.readDataFromPropertyFile().getKey(),
					HelperClass.readDataFromPropertyFile().getContentType(), PayloadToCreatePostRequest.postRequest(),
					HelperClass.readDataFromPropertyFile().getBaseURL(),
					HelperClass.readDataFromPropertyFile().getBasePathForPost());
			Object place_id = postResponse.jsonPath().get("place_id");
			testReport.get().log(Status.PASS, "place id is extracted");
			responseForDelete = JsonDeleteRequest.jsonDeleteRequest(
					HelperClass.readDataFromPropertyFile().getKey(),
					HelperClass.readDataFromPropertyFile().getContentType(), DeletePayload.deletePayload(place_id),
					HelperClass.readDataFromPropertyFile().getBaseURL(),
					HelperClass.readDataFromPropertyFile().getBasePathForDelete());
			Assert.assertEquals(200, responseForDelete.getStatusCode());
			testReport.get().log(Status.PASS, "validated the status code for delete");
			Response responseForGet = JSONGetRequest.getRequest(HelperClass.readDataFromPropertyFile().getKey(),
					HelperClass.readDataFromPropertyFile().getContentType(),
					HelperClass.readDataFromPropertyFile().getBaseURL(),
					HelperClass.readDataFromPropertyFile().getBasePathForGet(), place_id);
			System.out.println(responseForGet.getBody().asPrettyString());
			testReport.get().log(Status.PASS, "validated the get response post delete");
			Assert.assertEquals("Get operation failed, looks like place_id  doesn't exists",
					responseForGet.path("msg"));
			testReport.get().log(Status.PASS, "Deleted the customer details");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getStackTrace().toString());
//			ExceptionalHandlingForStatusCode.errorHandling(responseForDelete.statusCode());
		}
		
	}
	
}
