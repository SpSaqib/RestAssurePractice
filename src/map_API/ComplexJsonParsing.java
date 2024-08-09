package map_API;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParsing {

	public static void main(String[] args) 
	{
		JsonPath js=new JsonPath(payLoad.AddPlace1PayLoad.courseDetails());
		
    //		1. Print No of courses returned by API
		int count=js.getInt("courses.size()");
		System.out.println(count);
		
		// 2.Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		// 3. Print Title of the first course
		String firstCourseTitle = js.get("courses[0].title");
	//	String firstCourseTitle = js.getString("courses[0].title");
		// get method will written string only so we can use getString or get both the methods
		System.out.println(firstCourseTitle);
		
		// 4. Print All course titles and their respective Prices
		
		for(int i=0;i<count;i++)
		{
			String courseTitles = js.get("courses["+i+"].title");
			System.out.println(courseTitles);
			int coursePrice = js.getInt("courses["+i+"].price");
			System.out.println(coursePrice);
			
		}
		
		// 5. Print no of copies sold by RPA Course
		
		for(int i=0;i<count;i++)
		{
			if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA"))
			{
				int RPACopies = js.getInt("courses["+i+"].copies");
				System.out.println(RPACopies);
				break;
			}
			
		}
		

	}

}
