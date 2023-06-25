package com.practo.test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.practo.report.CreateReport;
import com.practo.report.HandlingBrowser;
import com.practo.report.ReadFromExcel;



public class AlertTest extends CreateReport{
	
	@DataProvider(name = "TestData")
	public Object[][] getTestData() {
		Object[][] data = ReadFromExcel.getExcelData();
		return data;
	}

	@Test(dataProvider = "TestData")
	public void alertMessage(String name, String compName, String email,String phone, String type) throws Exception {

		WebDriver driver = HandlingBrowser.launchBrowser();
		By ename = By.id("name");
		By orgName = By.id("organization_name");
		By e_mail = By.id("official_email_id");
		By phoneNo = By.id("official_phone_no");
		By orgSizeDropDown = By.id("organization_size");
		By scheduleDemoButton = By.id("button-style");

		WebDriverWait wait = new WebDriverWait(driver, 180);		
		test = extent.createTest("Alert Message Verification");
		driver.get("https://www.practo.com/plus/corporate");
		driver.manage().window().maximize();

		wait.until(ExpectedConditions.elementToBeClickable(scheduleDemoButton));
		driver.findElement(ename).sendKeys(name);
		driver.findElement(orgName).sendKeys(compName);
		driver.findElement(e_mail).sendKeys(email);
		driver.findElement(phoneNo).sendKeys(phone);
		Select size = new Select(driver.findElement(orgSizeDropDown));
		driver.findElement(orgSizeDropDown).click();
		size.selectByVisibleText("10001+");
		driver.findElement(scheduleDemoButton).click();
		wait.until(ExpectedConditions.alertIsPresent());
		System.out.println("Error Message:");		
		Alert alert = driver.switchTo().alert();
		String errMsg = alert.getText();
		System.out.println(errMsg);
		TimeUnit.SECONDS.sleep(3);
		alert.accept();

		String data = "Name: " + name + " || Company: " + compName	+ " || E-Mail: " + email + " || Phone: " + phone;

		test.log(Status.INFO, data);
		test.log(Status.INFO, "Test Type: " + type);

		switch (type) {
		case "Invalid Email":
			if (errMsg.equals("Please enter valid email address"))
				test.log(Status.PASS, errMsg);
			else {
				test.log(Status.FAIL, errMsg);
				AssertJUnit.fail();
			}
			break;

		case "Invalid Phone Number":
			if (errMsg.equals("Please enter valid phone no"))
				test.log(Status.PASS, errMsg);
			else {
				test.log(Status.FAIL, errMsg);
				AssertJUnit.fail();
			}
			break;

		case "Invalid Name":
			if (errMsg.equals("Please enter valid name"))
				test.log(Status.PASS, errMsg);
			else {
				test.log(Status.FAIL, errMsg);
				AssertJUnit.fail();
			}
			break;		
			
		}

		HandlingBrowser.closeBrowser(driver);
	}


}
