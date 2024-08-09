package bookLibrary;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import io.restassured.RestAssured;

public class StaticJson1StoringDataInFile {
	
	public static void main(String[] args) throws IOException {
		// Define the base URI for the RestAssured API
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		
			// Read the JSON file as a String
			String jsonBody = new String(Files.readAllBytes(Paths.get("C:\\Users\\HP\\Downloads\\StaticDatatoAddPlace.json")));
			
			// Make the POST request and validate the response
			given().log().all()
				.queryParam("key", "qaclick123")
				.header("Content-Type", "application/json")
				.body(Files.readAllBytes(Paths.get("C:\\Users\\HP\\Downloads\\StaticDatatoAddPlace.json")))
				.when().post("/maps/api/place/add/json")
				.then().log().all()
				.assertThat()
				.statusCode(200)
				.body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)");
				
		 
	}
}
