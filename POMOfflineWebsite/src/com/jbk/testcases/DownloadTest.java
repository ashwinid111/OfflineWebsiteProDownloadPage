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
import com.aventstack.extentreports.Status;
import com.jbk.page.DownloadPage;

public class DownloadTest extends TestBase {

	WebDriver driver = null;
	DownloadPage dp = null;
	// String className="DownloadPageTest";
	
	@BeforeTest
	public void beforeTest() {
		super.setReport();
	}

	@AfterTest
	public void afterTest() {
		super.endReport();
	}

	@BeforeMethod
	public void loadUrl() throws IOException {
	
		FileInputStream fis = new FileInputStream("propertyFile.properties");
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

	@AfterMethod
	public void closeBrowser() {

		testlogger.log(Status.INFO, "Browser Closed");
		driver.close();
	}

	@Test(priority = 1)
	public void validateCounOfHeaderColumns() throws Exception {
		super.passTest("validateCounOfHeaderColumns", "DownloadPageTest");
		Assert.assertTrue(dp.validateHeaderCount(testlogger));
		Thread.sleep(3000);
	}

	@Test(priority = 2)
	public void validateNoOfRowColumn() throws Exception {
		super.passTest("validateNoOfRowColumn", "DownloadPageTest");
		Assert.assertTrue(dp.checkTotalRowColNum(testlogger));
		Thread.sleep(3000);
	}

	@Test(priority = 3)
	public void validateFollowVender() throws Exception {
		super.passTest("validateFollowVender", "DownloadPageTest");
		Assert.assertTrue(dp.checkFollowVender(testlogger));
		Thread.sleep(3000);
	}

	@Test(priority = 4)
	public void check32bitLink() throws Exception {
		super.passTest("check32bitLink", "DownloadPageTest");
		Assert.assertTrue(dp.checkLink32bitClickable(testlogger));
		Thread.sleep(3000);
	}

	@Test(priority = 5)
	public void check64bitLink() throws Exception {
		super.passTest("check64bitLink", "DownloadPageTest");
		Assert.assertTrue(dp.checkLink64bitClickable(testlogger));
		Thread.sleep(3000);
	}

	@Test(priority = 6)
	public void checkOfficialWebsite() {
		super.passTest("CheckOfficialWebsite", "DownloadPageTest");
		Assert.assertTrue(dp.checkOfficialWebsiteClickable(testlogger));
	}

	@Test(priority = 8)
	public void validateVendorsNumberListSequence() {
		super.passTest("ValidateVendorsNumberListSequence", "DownloadPageTest");
		Assert.assertTrue(dp.checkVendorListIsSort(testlogger));
	}

	@Test(priority = 9)
	public void validateSrNumberListSequence() {
		super.passTest("ValidateSrNumberListSequence", "DownloadPageTest");
		Assert.assertTrue(dp.checkSrNumberListInSort(testlogger));
	}

	@Test(priority = 10)
	public void validateStartWithVendorName() {
		super.passTest("ValidateStartWithVendorName", "DownloadPageTest");
		Assert.assertTrue(dp.checkVendorStartWith(testlogger));
	}

}
