package map_API;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParsingSumValidation {
	
	@Test
	public void sumOfCourses()
	{
		JsonPath js=new JsonPath(payLoad.AddPlace1PayLoad.courseDetails());
		int count=js.getInt("courses.size()");
		
		int sum=0;
		
		for(int i=0;i<count;i++)
		{
			int courseCopies = js.getInt("courses["+i+"].copies");
			int coursePrice = js.getInt("courses["+i+"].price");
			
			int amount=coursePrice*courseCopies;
			
			sum=sum+amount;
			
		}
		System.out.println(sum);
		
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(purchaseAmount, sum);
	}

}
