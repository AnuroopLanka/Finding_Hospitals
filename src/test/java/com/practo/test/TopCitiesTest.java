package com.practo.test;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.practo.report.CreateReport;
import com.practo.report.HandlingBrowser;

public class TopCitiesTest extends CreateReport {
	
	@Test
	public static void countCities() throws Exception {

		WebDriver driver = HandlingBrowser.launchBrowser();
		By diagnosticsLinkText = By.xpath("//div[@class='product-tab__title'][normalize-space()='Lab Tests']");
		//div[text() = 'Diagnostics']
		By topCitiesText = By.xpath("//div[text() = 'TOP CITIES']");

		test = extent.createTest("Verifying Diagnostics Cities");

		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.get("https://www.practo.com/");
		wait.until(ExpectedConditions.elementToBeClickable(diagnosticsLinkText));
		driver.findElement(diagnosticsLinkText).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(topCitiesText));
		TimeUnit.SECONDS.sleep(3);
		List<WebElement> topCities = driver.findElements(By.xpath("//li[@class = 'u-text--center']/div[@class = 'u-margint--standard o-f-color--primary']"));
		String cities = "No. of Top Cities Retrieved: "+ Integer.toString(topCities.size());
		test.log(Status.INFO, cities);
		if (topCities.size() == 6) {
			test.log(Status.PASS, "Top Cities Retrieved Correctly");
		} 
		else {
			test.log(Status.FAIL, "Top Cities Not Retrieved Correctly");
			AssertJUnit.fail();
		}

		HandlingBrowser.closeBrowser(driver);
	}



}
