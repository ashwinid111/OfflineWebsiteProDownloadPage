package com.jbk.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
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

public class DownloadTestExtentReports {

	public WebDriver driver = null;
	public static Properties prop;
	public DownloadPage dp = null;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public ExtentTest test1;

	@BeforeTest
	public void startReport() {
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/DownloadPageExtentReport.html");
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Offline Website");
		extent.setSystemInfo("Environment", "Download Page Testing");
		extent.setSystemInfo("User Name", "AshwiniD");
	
		htmlReporter.config().setDocumentTitle("DownloadPage ");
		htmlReporter.config().setReportName("DownloadPageExtentReport ");
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}

	@BeforeMethod
	public void setUpBrowser() throws IOException {
		
		test1=extent.createTest("Download").createNode("testCase");
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
		//test.log(Status.INFO, "Browser Launched");
		dp = new DownloadPage(driver);
		dp.navigateToDownloadPage();
	}
	
	@Test(priority = 1)
	public void validateCounOfHeaderColumns() {
		
		test = extent.createTest("ValidateCounOfHeaderColumns");
		Assert.assertTrue(dp.validateHeaderCount(test));
		test.log(Status.PASS, "Test Case Passed is passTest");
	}

	@Test(priority = 2)
	public void validateNoOfRowColumn() {
		test = extent.createTest("ValidateNoOfRowColumn");
		Assert.assertTrue(dp.checkTotalRowColNum(test));
		test.log(Status.PASS, "Test Case Passed is passTest");
	}

	/*@Test(priority = 3)
	public void validateFollowVender() {
		test = extent.createTest("ValidateFollowVender");
		Assert.assertTrue(dp.checkFollowVender(test));
		test.log(Status.PASS, "Test Case Passed is passTest");
	}

	@Test(priority = 4)
	public void check32bitLink() {
		test = extent.createTest("Check32bitLink");
		test.assignCategory("Download Page Test Cases");
		Assert.assertTrue(dp.checkLink32bitClickable(test));
		test.log(Status.PASS, "Test Case Passed is passTest");
	}

	@Test(priority = 5)
	public void check64bitLink() {
		test = extent.createTest("Check64bitLink");
		Assert.assertTrue(dp.checkLink64bitClickable(test));
		test.log(Status.PASS, "Test Case Passed is passTest");
	}

	@Test(priority = 6)
	public void checkOfficialWebsite() {
		test = extent.createTest("CheckOfficialWebsite");
		Assert.assertTrue(dp.checkOfficialWebsiteClickable(test));
		test.log(Status.PASS, "Test Case Passed is passTest");
	}

	@Test(priority = 8)
	public void validateVendorsNameListSequence() {
		test = extent.createTest("ValidateVendorsNameListSequence");
		Assert.assertTrue(dp.checkVendorListIsSort(test));
		test.log(Status.PASS, "Test Case Passed is passTest");
	}

	@Test(priority = 9)
	public void validateSrNumberListSequence() {
		test = extent.createTest("ValidateSrNumberListSequence");
		Assert.assertTrue(dp.checkSrNumberListInSort(test));
		test.log(Status.PASS, "Test Case Passed is passTest");
	}

	@Test(priority = 10)
	public void validateVendorStartWithGoogle() {
		test = extent.createTest("ValidateVendorStartWithGoogle");
		Assert.assertFalse(dp.checkVendorStartWith(test));
		test.log(Status.PASS, "Test Case Passed is passTest");
	}
*/
	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Case Failed is " + result.getName());
			test.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case Skipped is " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case Passed is " + result.getName());
		}

		driver.close();
		test.log(Status.INFO, "Browser Closed");
	}
}
