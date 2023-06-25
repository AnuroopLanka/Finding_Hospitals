package com.practo.test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.*;

import com.aventstack.extentreports.Status;
import com.practo.report.CreateReport;
import com.practo.report.HandlingBrowser;


public class SearchResultsTest extends CreateReport{
	
	By location = By.xpath("//*[@id=\'c-omni-container\']/div/div[1]/div/input");
	By locationClearButton = By.xpath("//body/div[@id='root']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/span[2]/span[1]/i[1]");
	By searchType = By.xpath("//*[@id=\'c-omni-container\']/div/div[2]/div/input");
	By optionBLR = By.xpath("//div[contains(text(),'Bangalore')]");
	By optionHospital = By.xpath("//div[text() = 'Hospital']");
	By allFiltersButton = By.xpath("//span[@data-qa-id = 'all_filters']");
	String url = "https://www.practo.com/";
	
	public void citySearch(WebDriver driver) {
		driver.findElement(location).click();
		driver.findElement(locationClearButton).click();
		driver.findElement(location).sendKeys("Bangalore");
		WebDriverWait cityWait = new WebDriverWait(driver,10);
		cityWait.until(ExpectedConditions.elementToBeClickable(optionBLR));
		driver.findElement(optionBLR).click();	
	}
	
	public void serviceSearch(WebDriver driver) {
		
		driver.findElement(searchType).sendKeys("Hospital");
		WebDriverWait serviceWait = new WebDriverWait(driver,10);
		serviceWait.until(ExpectedConditions.elementToBeClickable(optionHospital));
		driver.findElement(optionHospital).click();
	}
	
	@Test
	public void checkSearchPage() throws Exception {

		WebDriver driver = HandlingBrowser.launchBrowser();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		test = extent.createTest("Verify Search Results");
		driver.get("https://www.practo.com/");
		driver.manage().window().maximize();
		try {
			citySearch(driver);
		} catch (Exception e) {

			try {
				citySearch(driver);
			} catch (Exception err) {
				err.printStackTrace();
				System.out.println("Execution Failed. \nPlease Retry.");
				driver.quit();
				AssertJUnit.fail();
				System.exit(-1);
			}
		}

		String expectedCity = "Bangalore";
		String actualCity = driver.findElement(location).getAttribute("value");

		AssertJUnit.assertEquals("Incorrect City", expectedCity, actualCity);

		test.log(Status.INFO, "Expected City: " + expectedCity);
		test.log(Status.INFO, "Actual City: " + actualCity);
		if (expectedCity.equalsIgnoreCase(actualCity)) {
			test.log(Status.PASS, "City Verification Successful.");
		} else {
			test.log(Status.FAIL, "City Verification Failed.");
		}

		try {
			serviceSearch(driver);
		} catch (Exception e) {

			driver.findElement(searchType).clear();
			try {
				serviceSearch(driver);
			} catch (Exception err) {
				err.printStackTrace();
				System.out.println("Execution Failed.Please Retry.");
				driver.quit();
				AssertJUnit.fail();
				System.exit(-1);
			}
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(allFiltersButton));

		String expectedTitle = "Best Hospitals in Bangalore - Book Appointment Online, View Fees, Reviews | Practo";
		String actualTitle = driver.getTitle();

		AssertJUnit.assertEquals("Wrong Page Opened.", expectedTitle,actualTitle);

		test.log(Status.INFO, "Expected Title: " + expectedTitle);
		test.log(Status.INFO, "Actual Title: " + actualTitle);
		if (expectedTitle.equalsIgnoreCase(actualTitle)) {
			test.log(Status.PASS, "Search Verification Successful.");
		}
		else{
			test.log(Status.FAIL, "Search Verification Failed.");
		}

		HandlingBrowser.closeBrowser(driver);

	}

}
