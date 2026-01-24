package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public XLUtility(String path)
	{
		this.path=path;
	}
	
	public int countRow(String sheetname) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		int row_no=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return row_no;
	}
	
	public int countCell(String sheetname,int rowno) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rowno);
		int cell_no=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cell_no;
	}
	
	public String getdata(String sheetname,int rowno,int colno) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rowno);
		cell=row.getCell(colno);
		DataFormatter format=new DataFormatter();
		String data;
		try {
			data=format.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	public void setdata(String sheetname,int rowno,int colno,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())  //it will create data if file not exist
		{
			workbook=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetname)==-1)
			workbook.createSheet(sheetname);
			sheet=workbook.getSheet(sheetname);
			
		if(sheet.getRow(rowno)==null)
			sheet.createRow(rowno);
		row=sheet.getRow(rowno);
		
		cell=row.createCell(colno);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	
	}
	
	public void fillGreenColor(String sheetname,int rowno,int colno) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		
		row=sheet.getRow(rowno);
		cell=row.getCell(colno);
		
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		}
	
	public void fillRedColor(String sheetname,int rowno,int colno) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		
		row=sheet.getRow(rowno);
		cell=row.getCell(colno);
		
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED
				.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		}

}
