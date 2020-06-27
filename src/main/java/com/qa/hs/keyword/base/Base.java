package com.qa.hs.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(String browsername) {
		if(browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\SELENIUM\\chromedriver_win32\\chromedriver.exe");
			if(prop.getProperty("headless").equals("yes")) {
				//headless mode
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			}else {
				driver= new ChromeDriver();
			}
		}
		return driver;
	}
   public Properties init_properties() throws IOException {
	   prop = new Properties();
	   FileInputStream ip = new FileInputStream("C:\\workspace\\eclipse\\KeyworkDrivenHubSpot\\src\\main\\java\\com\\qa\\hs\\keyword\\config\\config.properties");
	   prop.load(ip);
	   return prop;
   }
   
}
