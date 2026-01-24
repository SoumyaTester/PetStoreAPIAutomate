package api.endpoints;
import api.payload.User;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import io.restassured.response.Response;

//here we created this class to implement only CURD methods

public class UserEndpoint {
	//this method i have created to read URL from Properties file that is routes.properties(routes.p) 
	static ResourceBundle getUrl()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	public static Response create_user(User payload)
	{
		String post_url=getUrl().getString("post_url");//routes.p
		Response res=given()
			.contentType("application/json")
			.body(payload)
		
		.when()
			//.post(Routes.post_url);
			.post(post_url);			//routes.p
		return res;
		
	}
	
	public static Response read_user(String username)
	{
		Response res=given()
			.pathParam("username", username)
		.when()
			.get(Routes.get_url);
		return res;
		
	}
	
	public static Response update_user(String username,User payload)
	{
		Response res=given()
			.contentType("application/json")
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(Routes.put_url);
		return res;
	}
	
	public static Response delete_user(String username)
	{
		Response res=given()
			.pathParam("username", username)
		.when()
			.delete(Routes.del_url);
		return res;
		
	}

}
