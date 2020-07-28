package com.jbk.testcases;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.jbk.page.DownloadPage;

public class TestBase {
	public DownloadPage dp = null;
		public Properties prop;
		public ExtentHtmlReporter htmlReporter;
		public ExtentReports extent;
		public ExtentTest testlogger;
		public WebDriver driver;
		String pageName="DownloadPageTest";
		String uname="Ashwini";
		@BeforeTest
		public ExtentReports setReport(){

			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/DownloadPageExtentReport.html");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Host Name", "Offline Website");
			extent.setSystemInfo("Environment", "Download Page Testing");
			extent.setSystemInfo("User Name", "AshwiniD");
		
			htmlReporter.config().setDocumentTitle("DownloadPage ");
			htmlReporter.config().setReportName("DownloadPageExtentReport ");
			htmlReporter.config().setTheme(Theme.STANDARD);
			return extent;
		}

		@AfterTest
		public void endReport(){
		extent.flush();
		}

		@BeforeMethod
		public void setUpBrowser() throws IOException{
			String propPath = System.getProperty("user.dir") + "/propertyFile.properties";
			prop = new Properties();
			FileInputStream fis = new FileInputStream(propPath);
			Properties prop = new Properties();
			prop.load(fis);
			String URL = prop.getProperty("url");
			String setPath = prop.getProperty("path");
			System.setProperty("webdriver.chrome.driver", setPath);
			driver = new ChromeDriver();
			driver.get(URL);
			driver.manage().window().maximize();
			dp = new DownloadPage(driver);
			dp.navigateToDownloadPage();
		}
		
		public void passTest(String testName,String pageName)
		 {
		        testlogger = extent.createTest(testName,pageName);
		        Assert.assertTrue(true);
		 }
		
		 public void failTest(String testName,String pageName)
		 {
		        testlogger = extent.createTest(testName,pageName);
		        Assert.assertFalse(false);
		 }
		
		 public void skipTest(String testName)
		 {
		        testlogger = extent.createTest(testName);
		        throw new SkipException("Skipping this test with exception");
		 }
		    
		@AfterMethod
		public void getResult(ITestResult result) {
			if (result.getStatus() == ITestResult.FAILURE) {
				
				testlogger.log(Status.FAIL, "Test Case Failed is " + result.getName());
				testlogger.fail(result.getThrowable());
				
			} else if (result.getStatus() == ITestResult.SKIP) {
				
				testlogger.log(Status.SKIP, "Test Case Skipped is " + result.getName());
				
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				
				testlogger.log(Status.PASS, "Test Case Passed is " + result.getName());
			}
			testlogger.log(Status.INFO, "Browser Closed");
			driver.close();
		}
	}
