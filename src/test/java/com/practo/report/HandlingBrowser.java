package com.practo.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HandlingBrowser {
	
	static WebDriver driver = null;

	public static WebDriver launchBrowser() throws IOException {

		String propPath = System.getProperty("user.dir") + "\\config.properties";
		FileInputStream file = new FileInputStream(new File(propPath));
		Properties prop = new Properties();
		prop.load(file);
		file.close();

		String browser = prop.getProperty("testBrowser");

		if (browser.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		return driver;
	}

	public static void closeBrowser(WebDriver driver) {
		driver.quit();
	}

}
