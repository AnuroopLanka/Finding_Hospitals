package com.practo.pom;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.practo.main.FindHospitals;
import com.practo.utils.Highlighter;
import com.practo.utils.Screenshot;

public class FilteringHospitals extends FindHospitals {

	// Creating objects for the WebElements in the page
	By allFiltersButton = By.xpath("//span[@data-qa-id = 'all_filters']");
	By hasParkingButton = By.xpath("//div[@data-qa-id = 'Has_Parking_checkbox']");
	By checkbox_24x7 = By.xpath("//div[@data-qa-id='Open_24X7_checkbox']");

	// Function to set the filters
	public void setFilters(WebDriver driver) throws IOException, InterruptedException {

		logger = report.createTest("TestCase-3:Checking of visibility All filters dropdown box");
		logger.log(Status.INFO, "Starting the TestCase");
		WebElement filter = driver.findElement(allFiltersButton);
		Highlighter.highlightElement(filter, driver);
		logger.pass("All filters Dropdown box is visible");
		filter.click();
		logger.pass(" All Filters Dropdown box is selected");
		logger.log(Status.INFO, "TestCase executed successfully");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(hasParkingButton));
		
		logger = report.createTest("TestCase-4:Checking Parking Option is Clickable");
		logger.log(Status.INFO, "Starting the Test Case");
		WebElement park = driver.findElement(hasParkingButton);
		Highlighter.highlightElement(park, driver);
		logger.pass("Parking option is visible");
		park.click();
		logger.pass("Parking Option is selected");
		logger.log(Status.INFO, "TestCase executed Successfully");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='page-loader']")));
		wait.until(ExpectedConditions.elementToBeClickable(checkbox_24x7));
		
		logger = report.createTest("TestCase-5:Checking 24/7 Option is Clickable");
		logger.log(Status.INFO, "Starting the TestCase");
		WebElement cbox = driver.findElement(checkbox_24x7);
		Highlighter.highlightElement(cbox, driver);
		logger.pass("24/7 Option is visible");
		cbox.click();
		logger.pass("24/7 Option is Selected");
		logger.log(Status.INFO, "TestCase executed Successfully");
		Thread.sleep(3000);
		Screenshot.takeScreenshot(driver, "filters");
		Thread.sleep(3000);
		
		logger = report.createTest("TestCase-6:Searching Hospitals");
		logger.log(Status.INFO, "Starting the TestCase");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='page-loader']")));
		logger.pass("Hospitals Seacrhed for the required conditions");
		logger.log(Status.INFO, "TestCase completed Successfully");
	}

	// Function to scroll down till the last result
	public void scrollDown(WebDriver driver) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		int resultsPerPage = 11;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		while (true) {
			String lastElement = "//div[@data-qa-id = 'hospital_card']" + "[" + resultsPerPage + "]";
			jse.executeScript("window.scrollTo(0, document.body.scrollHeight - 10);");
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(lastElement)));
			} catch (TimeoutException e) {
				break;
			}
			resultsPerPage += 10;
		}

		jse.executeScript("window.scrollTo(0, 0);");

	}

	// Function to retrieve the hospital ratings and names
	public void fetchHospitalList(WebDriver driver) throws IOException {
		logger = report.createTest("TestCase-7:Displaying the hospitals");
		logger.log(Status.INFO, "Starting the TestCase");
		System.out.println("Hospitals with above 3.5 rating: ");
		List<WebElement> hospitalRatings = driver.findElements(By.xpath("//*[@data-qa-id = 'star_rating']"));
		logger.pass("Displayed Hospitals with selected Conditions");
		List<WebElement> hospitalNames = driver.findElements(By
				.xpath("//*[@data-qa-id = 'star_rating']/ancestor::div[@data-qa-id = 'hospital_card']/descendant::h2"));
		Map<String, String> hospitalData = new LinkedHashMap<String, String>();

		for (int i = 0; i < hospitalNames.size(); i++) {
			// Filtering hospitals with rating more than 3.5
			if (Double.parseDouble(hospitalRatings.get(i).getAttribute("title")) > 3.5) {
				hospitalData.put(hospitalNames.get(i).getText(), hospitalRatings.get(i).getAttribute("title"));
				System.out.println(hospitalNames.get(i).getText());
			}
		}
		// Writing the filtered data into Excel
		ExcelWrite obj = new ExcelWrite();
		obj.writeToExcel(hospitalData);
		logger.log(Status.INFO, "TestCase executed successfully");
	}

	// Function to call all functions
	public void getHospitalList(WebDriver driver) throws IOException, InterruptedException {

		setFilters(driver);
		scrollDown(driver);
		fetchHospitalList(driver);

	}

}
