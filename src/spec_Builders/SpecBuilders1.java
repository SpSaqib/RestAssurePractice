package spec_Builders;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import serialisation_pojo.Location;
import serialisation_pojo.Pojo1JSON;

public class SpecBuilders1 {

	public static void main(String[] args) {
		
		
		Pojo1JSON p=new Pojo1JSON();
		//Serialization
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("29, side layout, cohen 09");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		
		// Creating a List 
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		// Add created list set method
		p.setTypes(myList);
		
		// set data for set location method it expects return type as Location
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		
		// Spec builders for request specification
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		RequestSpecification res = given().spec(req)
		.body(p);
		
		// Spec builders for Response specification
		
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200)
		.expectContentType(ContentType.JSON).build();
		
		Response response=res.when().post("/maps/api/place/add/json")
		.then().spec(resSpec).extract().response();
		
		String responseString = response.asString();
		System.out.println(responseString);

	}

}
