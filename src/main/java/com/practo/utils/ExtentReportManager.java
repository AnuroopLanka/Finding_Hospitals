package com.practo.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager {
	
	public static ExtentReports report;
	
	public static ExtentReports getReportInstance() {
		
		if(report==null) {
			
			String reportName = DateUtils.getTimeStamp() + ".html"; // New report will be created with time and date as title
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReport\\"+reportName);
			report = new ExtentReports();
			report.attachReporter(htmlReporter);
			
			report.setSystemInfo("OS", "Windows 7");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("Build Number", "10.8.1");
			report.setSystemInfo("Broswer", "Chrome");
			
			htmlReporter.config().setDocumentTitle("Finding Hospitals");
			htmlReporter.config().setReportName("Practo Website-Finding Hospitals");
			htmlReporter.config().setTimeStampFormat("MMM dd,yyyy HH:mm:ss");
			
		}
				
		return report;
	}

}
