package map_API;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class AddPlaceAPI1 
{

	public static void main(String[] args) {
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payLoad.AddPlace1PayLoad.payLoadForAddPlace())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)");
		
		

		//we can add multiple headers, even body for response validation
		// EqualTo method To assert that a value in the response body matches an expected value.
	}

	

}
