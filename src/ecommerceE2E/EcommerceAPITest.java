package ecommerceE2E;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginContext;

import org.testng.Assert;

public class EcommerceAPITest {

	public static void main(String[] args) throws InterruptedException {
		
		// Login API call and take access token
		// Spec Builders
		RequestSpecification req = new RequestSpecBuilder()
		.setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
		
		// Serialisation
		LogInRequestPojo logInRequest=new LogInRequestPojo();
		logInRequest.setUserEmail("saqibpendari@gmail.com");
		logInRequest.setUserPassword("Saqib@123");
		
		RequestSpecification reqLogin = given().log().all().spec(req).body(logInRequest);
		
		// Deserialisation
		 LogInResponsePojo loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract()
		.as(LogInResponsePojo.class);
		 System.out.println(loginResponse.getToken());
		 String accessToken = loginResponse.getToken();
		 System.out.println(loginResponse.getUserId());
		 String userId=loginResponse.getUserId();
		
		 // Add product in Website and take Product id
		 
		 RequestSpecification addProductBaseReq = new RequestSpecBuilder()
		.setBaseUri("https://rahulshettyacademy.com")
		 .addHeader("Authorization", accessToken ).build();
		 
		 RequestSpecification reqAddProduct=given().log().all().spec(addProductBaseReq)
		 .param("productName", "Remote Car")
		 .param("productAddedBy", userId)
		 .param("productCategory", "fashion")
		 .param("productSubCategory", "Cars")
		 .param("productPrice", "3500000")
		 .param("productDescription", "Ferrari")
		 .param("productFor", "Men") 
		 // Adding Attachments in RestAssured
		 .multiPart("productImage", new File("C:\\Users\\HP\\Downloads\\Cars 03082024.png"));
		 
		 String reqAddProductResponse= reqAddProduct
		.when().post("/api/ecom/product/add-product")
		 .then().log().all().extract().response().asString();
		 JsonPath js=new JsonPath(reqAddProductResponse);
		 String productId = js.getString("productId");
		 System.out.println(productId);
		 
		 // How to create an Order and take order id
		 
		 RequestSpecification createOrderBaseReq = new RequestSpecBuilder()
		.setBaseUri("https://rahulshettyacademy.com")
		.addHeader("Authorization", accessToken )
		.setContentType(ContentType.JSON).build();
		
		 OrderDetails orderDetails=new OrderDetails();
		 orderDetails.setCountry("India");
		 orderDetails.setProductOrderedId(productId);
		 
		 List<OrderDetails> orderDetailsList=new ArrayList<OrderDetails>();
		 orderDetailsList.add(orderDetails);
		 
		 Orders orders=new Orders();
		 orders.setOrders(orderDetailsList);
		 
		 RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(orders);
		 
		 String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order")
		 .then().log().all().extract().response().asString();
		 System.out.println(responseAddOrder);
		
		 // How to delete the product in an Application
		 
		 RequestSpecification deleteProductBaseReq = new RequestSpecBuilder()
		.setBaseUri("https://rahulshettyacademy.com")
		.addHeader("Authorization", accessToken ).setContentType(ContentType.JSON).build();
		 
		 // Add thread for 10 seconds to see whether the product is added or not
		 Thread.sleep(10000);
		
		
		 RequestSpecification deleteProdReq = given().log().all().spec(deleteProductBaseReq)
		.pathParam("productId", productId);
		
		 // in {productId} will check as path parameter
		 String deleteProductResponse = deleteProdReq
		.when().delete("/api/ecom/product/delete-product/{productId}")
		.then().log().all().extract().response().asString();
		 
		 
		 
		 JsonPath js1=new JsonPath(deleteProductResponse);
		 
		 
		 
		 
		 //Add assertion
		 assertEquals("Product Deleted Successfully",js1.getString("message"));

	}

}
