package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class UserDataProvider {
	
	@DataProvider(name="allData")
	public String[][] getallData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testdata/userDataDriven.xlsx";
		XLUtility xl=new XLUtility(path);
		
		int row_count=xl.countRow("Sheet1");
		int col_count=xl.countCell("Sheet1",1);
		
		String apidata[][]=new String[row_count][col_count];
		
		for(int i=1;i<=row_count;i++)
		{
			for(int j=0;j<col_count;j++)
				
			{
				apidata[i-1][j]=xl.getdata("Sheet1", i, j);
			}
		}
		
		return apidata;
	}
	
	@DataProvider(name="userName")
	public String[] getusername() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testdata/userDataDriven.xlsx";
		XLUtility xl=new XLUtility(path);
		
		int row_count=xl.countRow("Sheet1");
		
		String apidata[]=new String[row_count];
		
		for(int i=1;i<=row_count;i++)
		{
				apidata[i-1]=xl.getdata("Sheet1", i, 1);
		}
		return apidata;
	}
}
