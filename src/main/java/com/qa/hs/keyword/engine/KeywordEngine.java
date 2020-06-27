package com.qa.hs.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hs.keyword.base.Base;
import com.sun.media.sound.InvalidFormatException;

public class KeywordEngine {
	public WebDriver driver;
	public Properties prop;
	public static Workbook book;
	public static Sheet sheet;
	public final String SCENARIO_SHEET_PATH = "";
	public Base base;
	WebElement element;
	public void startExecution(String sheetName) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
		FileInputStream file = null;
		String loacatorName = null;
		String locatorValue = null;
		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		}catch(InvalidFormatException e) {
			e.printStackTrace();
		}
		sheet  = book.getSheet(sheetName);
		int k=0;
		for(int i=0;i<sheet.getLastRowNum();i++) {
			//teststep locatortype locatorvalue action value
			String locatorCovalue = sheet.getRow(i+1).getCell(k+1).toString().trim();
		          if(locatorCovalue.equalsIgnoreCase("NA")) {
		        	  loacatorName = locatorCovalue.split("=")[0].trim();//id
		        	  locatorValue = locatorCovalue.split("=")[1].trim();//username
		          	          
		          
		          }
		          String action = sheet.getRow(i+1).getCell(k+2).toString().trim();
		          String value = sheet.getRow(i+1).getCell(k+3).toString().trim();
		          switch(action) {
		          case "open browser":
		        	  base = new Base();
		        	  prop = base.init_properties();
		        	   if(value.isEmpty() || value.equals("NA")) {
		        		   base.init_driver(prop.getProperty("browser"));
		        	   }else {
		        		   driver = base.init_driver(value);
		        	   }
		        	  break;
		          case "enter url":
		        	  if(value.isEmpty() || value.equals("NA")) {
		        		  driver.get(prop.getProperty("url"));
		        	  }else {
		        		  driver.get(value);
		        	  }
		        	  break;
		          case "quit": 
		        	   driver.quit();
		        	   break;
		          default: 	        		  
		        		  break;
		        	  
		          }
		          switch(loacatorName) {
		          case "id":  element = driver.findElement(By.id(locatorValue));
		                      if(action.equalsIgnoreCase("sendkeys")){
		                    	  element.sendKeys(value);
		                      }else if(action.equalsIgnoreCase("click")) {
		                    	  element.click();
		                      }
		                      loacatorName = null;
		                     break; 		
		          case "linkText": element = driver.findElement(By.linkText(locatorValue));
		                            element.click();
		                            loacatorName = null;
		                            break;
		          default:break;
		          
		          }
		          
		}
	}

}
