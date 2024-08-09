package bookLibrary;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamicJson1 
{
	
	@Test
	public void addBook()
	{
		RestAssured.baseURI="http://216.10.245.166";
		
		String response = given().header("Content-Type","application/json")
		.body(payLoad.AddPlace1PayLoad.addBook("EFGB", "1235"))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = payLoad.ReUsableMethods.rawToJson(response);
		 String id = js.get("ID");
		 System.out.println(id);
	}

}
