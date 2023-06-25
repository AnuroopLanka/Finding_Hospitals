package com.practo.main;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.practo.pom.BrowserChoice;
import com.practo.pom.Corporates;
import com.practo.pom.DiagnosticCities;
import com.practo.pom.FilteringHospitals;
import com.practo.pom.HomePage;
import com.practo.utils.ExtentReportManager;

public class FindHospitals {
	
	public static ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//Function calls to invoke browser
		WebDriver driver = BrowserChoice.launchBrowser();
		
		//Function call to provide city and service type
		HomePage obj_home = new HomePage();
		obj_home.getHomePageSearch(driver);	
		TimeUnit.SECONDS.sleep(5);
		
		//Function call to filter hospitals above 3.5 stars
		FilteringHospitals obj_filter = new FilteringHospitals();
		obj_filter.getHospitalList(driver);
		TimeUnit.SECONDS.sleep(5);
		
		//Function call to obtain the top diagnostic cities
		DiagnosticCities obj_dc = new DiagnosticCities();
		obj_dc.getDiagnosticCities(driver);
		TimeUnit.SECONDS.sleep(5);
		
		//Function call to get the Alert Message displayed
		Corporates obj_corp = new Corporates();
		obj_corp.getAlertMessage(driver);
		TimeUnit.SECONDS.sleep(5);
		
		//Closing the browser
		report.flush();
		driver.close();
		driver.quit();
		
	}		

	}


