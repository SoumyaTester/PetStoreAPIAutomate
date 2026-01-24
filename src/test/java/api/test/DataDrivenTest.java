package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoint;
import api.payload.User;
import api.utilities.UserDataProvider;
import io.restassured.response.Response;

public class DataDrivenTest {
	@Test(priority=1,dataProvider="allData",dataProviderClass=UserDataProvider.class)
	public void postTestDD(String uid,String uname,String fname,String lname,String email,String pass,String ph)
	{
		User data=new User();
		data.setId(Integer.parseInt(uid));
		data.setUsername(uname);
		data.setFirstName(fname);
		data.setLastName(lname);
		data.setEmail(email);
		data.setPassword(pass);
		data.setPhone(ph);
		
		Response response=UserEndpoint.create_user(data);
		Assert.assertEquals(response.statusCode(),200);
		System.out.println("----post end--------");
	}
	
	@Test(priority=2,dataProvider="userName",dataProviderClass=UserDataProvider.class)
	public void getTestDD(String uname)
	{
		Response response=UserEndpoint.read_user(uname);
		response.then().statusCode(200);
		System.out.println("----get end--------");
	}

	@Test(priority=3,dataProvider="userName",dataProviderClass=UserDataProvider.class)
	public void deleteTestDD(String uname)
	{
		Response response=UserEndpoint.delete_user(uname);
		response.then().statusCode(200);
		System.out.println("----delete end--------");
	}

}
