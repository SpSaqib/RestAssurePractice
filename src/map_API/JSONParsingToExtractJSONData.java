package map_API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JSONParsingToExtractJSONData {

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

	}

}
