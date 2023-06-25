package com.practo.pom;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.practo.main.FindHospitals;
import com.practo.utils.Highlighter;
import com.practo.utils.Screenshot;

public class Corporates extends FindHospitals {

	// Creating objects for the WebElements in the page
	By forProvidersDropDown = By.xpath("//span[text() = 'For Providers']");

	By corporateWellnessLinkText = By.xpath("//a[text() = 'Corporate wellness']");
	// By name = By.xpath("//input[@id='name']");
	By name = By.xpath("//*[@id='name']");
	By orgName = By.id("organization_name");
	By email = By.id("official_email_id");
	By phoneNo = By.id("official_phone_no");
	By orgSizeDropDown = By.id("organization_size");
	By scheduleDemoButton = By.id("button-style");

	// Function to switch to a new tab
	public void tabSwitch(WebDriver driver) {
		
		logger = report.createTest("TestCase-11: Validating Corporate Wellness Link Text");
		logger.log(Status.INFO, "Starting the TestCase");
		HomePage obj = new HomePage();
		obj.getURL(driver);
		String parentHandle = driver.getWindowHandle();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(forProvidersDropDown));

		driver.findElement(forProvidersDropDown).click();
		logger.pass("Providers dropdown box is clicked");
		driver.findElement(corporateWellnessLinkText).click();
		logger.pass("Corporate wellness link is clicked");
		logger.pass("Corporate wellness Webpage is opened");
		logger.pass("Successfully swithched to another Tab");
		logger.log(Status.INFO, "TestCase executed successfully");
		logger.log(Status.INFO, "Test Case executed successfully");

		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> itr = tabs.iterator();
		while (itr.hasNext()) {
			String childHandle = itr.next();
			if (!parentHandle.equals(childHandle)) {
				driver.switchTo().window(childHandle);
			}
		}
	}


	// Function to fill the form and submit
	public void fillDetails(WebDriver driver, String ename, String org_name, String e_mail, String phone_No)
			throws IOException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		logger = report.createTest("TestCase-12:Validating Name textbox");
		logger.log(Status.INFO, "Starting the Test Case");
		WebElement nam = driver.findElement(By.xpath("//input[@id='name']"));
		Highlighter.highlightElement(nam, driver);
		nam.sendKeys(ename);
		logger.pass("Name is entered");
		logger.log(Status.INFO, "TestCase executed successfully");
		logger = report.createTest("TestCase-13:Validating  Organisation Name textbox");
		logger.log(Status.INFO, "Starting the Test Case");
		WebElement org = driver.findElement(By.xpath("//input[@id='organization_name']"));
		Highlighter.highlightElement(org, driver);
		org.sendKeys(org_name);
		logger.pass("Oragnization name is entered");
		logger.log(Status.INFO, "TestCase executed successfully");
		
		logger = report.createTest("TestCase-14:Validating e-mail id textbox");
		logger.log(Status.INFO, "Starting the Test Case");
		WebElement mail = driver.findElement(By.xpath("//input[@id='official_email_id']"));
		Highlighter.highlightElement(mail, driver);
		mail.sendKeys(e_mail);
		logger.pass("Invalid Email Id is entered");
		
		logger.log(Status.INFO, "TestCase executed successfully");
		logger = report.createTest("TestCase-15:Validating official number textbox");
		logger.log(Status.INFO, "Starting the Test Case");
		WebElement phno = driver.findElement(By.xpath("//input[@id='official_phone_no']"));
		Highlighter.highlightElement(phno, driver);
		phno.sendKeys(phone_No);
		logger.info("Invalid Contact number is entered");
		logger.log(Status.INFO, "TestCase executed successfully");
		
		logger = report.createTest("TestCase-16:Validating Organiation size dropdown");
		logger.log(Status.INFO, "Starting the Test Case");
        Select size = new Select(driver.findElement(orgSizeDropDown));
		WebElement dropdown = driver.findElement(orgSizeDropDown);
		dropdown.click();
		logger.info("Dropdown button is clicked");
		logger.log(Status.INFO, "TestCase executed successfully");
		
		logger = report.createTest("TestCase-17: Selecting Organiation size from dropdown ");
		logger.log(Status.INFO, "Starting the Test Case");
		size.selectByVisibleText("10001+");
		Highlighter.highlightElement(dropdown, driver);
		logger.pass("Organization size is selected");
		logger.log(Status.INFO, "TestCase executed successfully");
		
		Screenshot.takeScreenshot(driver, "corporate details");
		Thread.sleep(5000);
		logger = report.createTest("TestCase-18: Selecting Organiation size from dropdown ");
		logger.log(Status.INFO, "Starting the Test Case");
		driver.findElement(scheduleDemoButton).click();
		logger.pass("Shedule a Demo Button is clicked");
		logger.log(Status.INFO, "TestCase executed successfully");

	}

	// Function to call other functions and print the alert message
	public void getAlertMessage(WebDriver driver) throws IOException, InterruptedException {
		
		logger = report.createTest("Test case 19:Handling Alert");
		logger.log(Status.INFO, "Starting the TestCase");
		tabSwitch(driver);
		fillDetails(driver, "Sathyadev", "Cognizant", "sathya@cognizant", "984149854");

		WebDriverWait wait = new WebDriverWait(driver, 50);
		logger = report.createTest("Test case 19:Handling Alert");
		logger.log(Status.INFO, "Starting the TestCase");
		Alert alt = wait.until(ExpectedConditions.alertIsPresent());
		// Screenshot.takeScreenshot(driver,"alert");

		System.out.println("\nError Message:");
		System.out.println(alt.getText());
		TimeUnit.SECONDS.sleep(8);
		Thread.sleep(20000);
		alt.accept();
		
		logger.pass("Alert is handled");
		logger.log(Status.INFO, "TestCase executed Successfully");

	}

}
