package OAuthClientCredentials;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Get_Course_Details {
	
	public static void main(String[] args) {
		
		// Define the base URI for the OAuth server
		RestAssured.baseURI = "https://rahulshettyacademy.com";
	
		// Obtain the access token
		String response=given()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().log().all()
	//	.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
//	    or
		.post("/oauthapi/oauth2/resourceOwner/token").asString();
		
		System.out.println(response);
		
		// Parse the access token from the response
		JsonPath js=new JsonPath(response);
		 String accessToken = js.getString("access_token");
		 
		 System.out.println(accessToken);
		 
		// Use the access token to fetch course details
		String response2 = given()
		 .queryParam("access_token", accessToken)
		 .when().log().all()
	//	 .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
	//    or
		 .get("/oauthapi/getCourseDetails").asString();
		
		System.out.println(response2);
		
	}

}
