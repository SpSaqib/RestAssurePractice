package Jira_Project_APIs;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class CreateBugPOST {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://testingsaqib.atlassian.net";
		
		String createBUG=given()
		.header("Content-Type","application/json")
		.header("Authorization","Basic dGVzdGluZ3NhcWliQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBROHRUWTN4VFhyeUFaUGR4NldtTEloVW5Vc29pYl9ITWdzTDA5Uk9GMEdSZmx4aG9EMTlpV0JnNVNGMVp0aUllc1BDNmFuX2JZTEs5VlA3eWZLcnA5VERTamswMldVMkFYY3pQeVFkeGJ3Qmp2ekZfWm1FVGJDTEhFc3VueVNDN0dEYTlob0ZXRFZEX2VfYlNEMVplTTVJYlJJcG03OTRDTktnYlcxU0U3ZHM9QzkyOUFCOTA=")
        .body("{\r\n"
        		+ "    \"fields\": {\r\n"
        		+ "       \"project\":\r\n"
        		+ "       {\r\n"
        		+ "          \"key\": \"SCRUM\"\r\n"
        		+ "       },\r\n"
        		+ "       \"summary\": \"Button is working properly\",\r\n"
        		+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n"
        		+ "       \"issuetype\": {\r\n"
        		+ "          \"name\": \"Bug\"\r\n"
        		+ "       }\r\n"
        		+ "   }")
        .when().post("/rest/api/3/issue")
        .then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	}

}
