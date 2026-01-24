package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import api.endpoints.UserEndpoint;
import api.payload.User;
import io.restassured.response.Response;

public class TestUser {
	Faker fake;
	User payload_data;
	public Logger logs;
	
	@BeforeClass
	public void setupdata()
	{
		 fake=new Faker();
		 payload_data=new User();
		 
		 payload_data.setId(fake.idNumber().hashCode());
		 payload_data.setUsername(fake.name().username());
		 payload_data.setFirstName(fake.name().firstName());
		 payload_data.setLastName(fake.name().lastName());
		 payload_data.setEmail(fake.internet().emailAddress());
		 payload_data.setPassword(fake.internet().password());
		 payload_data.setPhone(fake.phoneNumber().cellPhone());
		 
		  logs=LogManager.getLogger(this.getClass());
		  
		 
	}
	
	
	@Test(priority=1)
	public void testPostUser()
	{
		logs.info("**********Post testcase started*******************");
		Response response=UserEndpoint.create_user(payload_data);
		response.then().header("Content-Type","application/json");
		response.then().statusCode(200);
		response.then().log().all();
		logs.info("**********Post testcase ended*******************");
	
	}
	@Test(priority=2)
	public void testGetUser()
	{
		logs.info("**********Get testcase started*******************");
		Response response=UserEndpoint.read_user(this.payload_data.getUsername());
		response.then().statusCode(200);
		Assert.assertEquals(response.contentType(),"application/json");
		response.then().log().all();
		logs.info("**********Get testcase ended*******************");
	}
	@Test(priority=3)
	public void testPutUser()
	{
		logs.info("**********Put testcase started*******************");
		payload_data.setFirstName(fake.name().firstName());
		payload_data.setLastName(fake.name().lastName());
		payload_data.setEmail(fake.internet().emailAddress());
		payload_data.setPassword(fake.internet().password());
		
		Response response=UserEndpoint.update_user(this.payload_data.getUsername(),payload_data);
		Assert.assertEquals(response.contentType(),"application/json");
		response.then().statusCode(200);
		response.then().log().all();
		
		Response responseupdated=UserEndpoint.read_user(this.payload_data.getUsername());
		responseupdated.then().log().body();
		Assert.assertEquals(responseupdated.statusCode(),200);
		logs.info("**********Put testcase ended*******************");
		
	
	}
	@Test(priority=4)
	public void testDeleteUser()
	{
		logs.info("**********Delete testcase started*******************");
		Response response=UserEndpoint.delete_user(this.payload_data.getUsername());
		response.then().statusCode(200);
		Assert.assertEquals(response.contentType(),"application/json");
		response.then().log().all();
		logs.info("**********Delete testcase ended*******************");
	}
	
}
