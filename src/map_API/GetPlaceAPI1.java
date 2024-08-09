package map_API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetPlaceAPI1 {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payLoad.AddPlace1PayLoad.payLoadForAddPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		// Extract the JSON data use extract()
		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
	//	Here response is in JSON format JSON is used to extract the exact
		JsonPath js=new JsonPath(response);
		
		String placeID = js.getString("place_id");
		System.out.println(placeID);
		
		// Update address
		
		String newAddress="Boston USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place API
		
		String getResponse=given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeID)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
	//	JsonPath js1=new JsonPath(getResponse);
		JsonPath js1 = payLoad.ReUsableMethods.rawToJson(getResponse);
		String actualAddress = js1.getString("address");
		
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);

	}

}
