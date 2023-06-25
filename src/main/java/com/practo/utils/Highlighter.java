package com.practo.utils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

	public class Highlighter {

	    public static void highlightElement(WebElement element, WebDriver driver) {
	        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	        jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')",
	                element);
	    }

}
