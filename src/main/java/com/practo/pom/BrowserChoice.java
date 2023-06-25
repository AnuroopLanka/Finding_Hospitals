package com.practo.pom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class BrowserChoice
{
	
	static WebDriver driver = null;

	//Function to invoke the WebDriver
	public static WebDriver launchBrowser() throws IOException {
	
	//Fetching data from the config.properties file
			String propPath = System.getProperty("user.dir") + "\\config.properties";
			FileInputStream file = new FileInputStream(new File(propPath));
			Properties prop = new Properties();
			prop.load(file);
			file.close();

			String browser = prop.getProperty("testBrowser");

			//Invoking the driver according to the browser mentioned in config.properties file
			if (browser.equalsIgnoreCase("chrome")) {
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			}

			else if (browser.equalsIgnoreCase("firefox")) {

				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}

						  else if (browser.equalsIgnoreCase("opera")) {
			  
			  System.setProperty("webdriver.opera.driver",
			  System.getProperty("user.dir")+"\\drivers\\operadriver.exe"); 
			  driver = new OperaDriver(); }
			 
			return driver;
		}

		//Function to close the browser
		public static void closeBrowser(WebDriver driver) {
			driver.quit();
		}
}
