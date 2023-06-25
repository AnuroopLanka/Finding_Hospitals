package com.practo.pom;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.practo.main.FindHospitals;
import com.practo.utils.Highlighter;
import com.practo.utils.Screenshot;

public class HomePage extends FindHospitals {

	// Creating objects for the WebElements in the page
	By location = By.xpath("//*[@id=\'c-omni-container\']/div/div[1]/div/input");
	By locationClearButton = By.xpath(
			"//body/div[@id='root']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/span[2]/span[1]/i[1]");
	By searchType = By.xpath("//*[@id=\'c-omni-container\']/div/div[2]/div/input");
	By optionBLR = By.xpath("//div[contains(text(),'Bangalore')]");
	By optionHospital = By.xpath("//div[text() = 'Hospital']");

	String url = "https://www.practo.com/";

	// Function to navigate to the URL
	public void getURL(WebDriver driver) {
		driver.manage().window().maximize();
		driver.get(url);
	}

	// Function to choose the city
	public void enterCity(WebDriver driver) {
		logger = report.createTest("TestCase-1:Selecting the Location");
		logger.log(Status.INFO, "Starting the TestCase");
		driver.findElement(location).click();
		logger.pass("Location text box is clicked");
		driver.findElement(locationClearButton).click();
		logger.pass("Cleared the previuos data");
		WebElement loc = driver.findElement(location);
		Highlighter.highlightElement(loc, driver);
		loc.sendKeys("Bangalore");
		logger.pass("Location is entered");
		WebDriverWait cityWait = new WebDriverWait(driver, 10);
		cityWait.until(ExpectedConditions.elementToBeClickable(optionBLR));
		driver.findElement(optionBLR).click();
		logger.pass("Location is selected");
		logger.log(Status.INFO, "TestCase executed successfully");
	}

	// Function to choose service type
	public void enterServiceType(WebDriver driver) throws IOException, InterruptedException {
		logger = report.createTest("TestCase-2:Selecting the Service");
		logger.log(Status.INFO, "Starting the TestCase");
		WebElement hosp1 = driver.findElement(searchType);
		Highlighter.highlightElement(hosp1, driver);
		hosp1.sendKeys("Hospital");
		logger.pass("Service is entered");
		WebDriverWait serviceWait = new WebDriverWait(driver, 10);
		serviceWait.until(ExpectedConditions.elementToBeClickable(optionHospital));
		Thread.sleep(3000);
		Screenshot.takeScreenshot(driver, "home");
		Thread.sleep(3000);
		WebElement hosp2 = driver.findElement(optionHospital);
		Highlighter.highlightElement(hosp2, driver);
		hosp2.click();
		logger.pass("Service is selected");
		logger.log(Status.INFO, "Test Case executed Successfully");
	}

	// Function to call all functions
	public void getHomePageSearch(WebDriver driver) throws IOException, InterruptedException {

		getURL(driver);
		enterCity(driver);
		enterServiceType(driver);
	}

}
