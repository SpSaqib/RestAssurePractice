package bookLibrary;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamiccJson2 {
	
	@Test(dataProvider="BooksData")
	
	public void addBook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		
		String response = given().header("Content-Type","application/json")
		.body(payLoad.AddPlace1PayLoad.addBook(isbn, aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = payLoad.ReUsableMethods.rawToJson(response);
		 String id = js.get("ID");
		 System.out.println(id);
	}
	
	@DataProvider(name="BooksData")
	public Object[] [] getData()
	{
		return new Object[] [] { {"SSPP","0001"},{"SSPQ","0002"},{"SSPR","0003"}};
	}

}
