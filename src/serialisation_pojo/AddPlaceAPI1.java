package serialisation_pojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AddPlaceAPI1 {

	public static void main(String[] args) {


		RestAssured.baseURI="https://rahulshettyacademy.com";
		
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
		
		Response res = given().log().all().queryParam("key", "qaclick123")
		.body(p)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response();
		
		String responseString = res.asString();
		System.out.println(responseString);

	}

}
