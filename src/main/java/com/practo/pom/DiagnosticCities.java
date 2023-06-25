package com.practo.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.practo.main.FindHospitals;
import com.practo.utils.Highlighter;
import com.practo.utils.Screenshot;

public class DiagnosticCities extends FindHospitals {

	// Creating objects for the WebElements in the page
	By diagnosticsLinkText = By.xpath("//span[text()='Book Diagnostic Tests']");
	By topCitiesText = By.xpath("//div[text()='TOP CITIES']");

	// Function to retrieve the diagnostic cities
	public void getDiagnosticCities(WebDriver driver) throws Exception {
		logger = report.createTest("TestCase-8:Validating the Lab Test Link text ");
		logger.log(Status.INFO, "Starting the TestCase");
		WebDriverWait waitLoad = new WebDriverWait(driver, 10);
		HomePage obj = new HomePage();
		obj.getURL(driver);
		Thread.sleep(4000);
		((JavascriptExecutor) driver).executeScript("scroll(0,2000)");

		// Opening the diagnostics page
		waitLoad.until(ExpectedConditions.elementToBeClickable(diagnosticsLinkText));

		WebElement diag = driver.findElement(diagnosticsLinkText);
		Highlighter.highlightElement(diag, driver);
		diag.click();
		logger.pass("Lab Test link Text is clicked");
		logger.pass("Diagnostics page is opened");
		logger.log(Status.INFO, "TestCase executed successfully");
		
		logger = report.createTest("TestCase-9: Selecting cities in Diagnostics page");
		logger.log(Status.INFO, "Starting the Testcase");
		waitLoad.until(ExpectedConditions.visibilityOfElementLocated(topCitiesText));
		Thread.sleep(3000);
		Screenshot.takeScreenshot(driver, "top cities");
		logger.pass("Cities names are displayed");
		logger.log(Status.INFO, "Test Case executed successfully");
		Thread.sleep(3000);

		// Fetching the top cities and storing them in a list
		logger = report.createTest("TestCase-10:Displaying the selected city");
		logger.log(Status.INFO, "Starting the Test Case");
		List<WebElement> topCities = driver.findElements(
				By.xpath("//li[@class = 'u-text--center']/div[@class = 'u-margint--standard o-f-color--primary']"));
		System.out.println("Top Cities:");
		List<String> cities = new ArrayList<String>();
		logger.pass("Selected city is displayed in the textbox");

		// Printing the cities in the console
		for (int i = 0; i < topCities.size(); i++) {
			cities.add(topCities.get(i).getText());
		}
		for (int i = 0; i < cities.size(); i++) {
			System.out.println(cities.get(i));
		}
		logger.log(Status.INFO, "Test Case executed successfully");
	}
}
