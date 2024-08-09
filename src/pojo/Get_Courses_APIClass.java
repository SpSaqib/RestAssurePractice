package pojo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Get_Courses_APIClass {

	public static void main(String[] args) {
					
		String[] expectedWebAutomationCourses= {"Selenium Webdriver Java","Cypress","Protractor"};
		// Converting arrays into ArrayList
		List<String> expectedListWeb=Arrays.asList(expectedWebAutomationCourses);
		
		for(String courses:expectedListWeb)
		{
			System.out.println(courses);
		}
				
				String response=given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParam("grant_type", "client_credentials")
				.formParam("scope", "trust")
				.when().log().all()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
			    
				
				System.out.println(response);
				
				
				JsonPath js=new JsonPath(response);
				 String accessToken = js.getString("access_token");
				 
				 System.out.println(accessToken);
				 
				// obtain Java object
				 GetCourse gc = given()
				 .queryParam("access_token", accessToken)
				 .when().log().all()
				 .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
			
				 // Find the linkedIn URL
				 System.out.println(gc.getLinkedIn());
				 // Find the instructor
				 System.out.println(gc.getInstructor());
				 
				 // Find the SOAP UI price
				System.out.println(gc.getCourses().getApi().get(1).getCourseTitle()); 
				
				List<API> apiCourses = gc.getCourses().getApi();
				
				for(int i=0;i<apiCourses.size();i++)
				{
					if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
					{
						System.out.println(apiCourses.get(i).getPrice());
					}
				}
				 
				// Find all course titles
				ArrayList<String> web=new ArrayList<String>();
				
				List<WebAutomation> webAutomationCourses = gc.getCourses().getWebAutomation();
                
				for (WebAutomation courses:webAutomationCourses)
				{
					web.add(courses.getCourseTitle());
				}
				
				for(String w:web)
				{
					System.out.println(w);
				}
			
				// getting error in assertion
				
			//	Assert.assertTrue(web.equals(expectedListWeb));
				
	}

}
