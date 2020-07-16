package com.jbk.testcases;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jbk.page.DownloadPage;

public class DownloadTest {

	WebDriver wd = null;
	DownloadPage dp = null;

	@BeforeMethod
	public void loadUrl() throws IOException {
		FileInputStream fis = new FileInputStream("propertyFile.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String URL = prop.getProperty("url");
		String setPath = prop.getProperty("path");
		System.setProperty("webdriver.chrome.driver", setPath);
		wd = new ChromeDriver();
		wd.get(URL);
		wd.manage().window().maximize();
		dp = new DownloadPage(wd);
		dp.navigateToDownloadPage();
	}

	@AfterMethod
	public void closeBrowser() {
		wd.close();
	}

	@Test(priority = 1)
	public void validateCounOfHeaderColumns() {
		Assert.assertTrue(dp.validateHeaderCount());
	}

	@Test(priority = 2)
	public void validateNoOfRowColumn() {
		Assert.assertTrue(dp.checkTotalRowColNum());
	}

	@Test(priority = 3)
	public void validateFollowVender() {
		Assert.assertTrue(dp.checkFollowVender());
	}

	@Test(priority = 4)
	public void check32bitLink() {
		Assert.assertTrue(dp.checkLink32bitClickable());
	}

	@Test(priority = 5)
	public void check64bitLink() {
		Assert.assertTrue(dp.checkLink64bitClickable());
	}

	@Test(priority = 6)
	public void checkOfficialWebsite() {
		Assert.assertTrue(dp.checkOfficialWebsiteClickable());
	}

	@Test(priority = 8)
	public void validateVendorsNumberListSequence() {
		Assert.assertTrue(dp.checkVendorListIsSort());
	}

	@Test(priority = 9)
	public void validateSrNumberListSequence() {
		Assert.assertTrue(dp.checkSrNumberListInSort());
	}

	@Test(priority = 9)
	public void validateStartWithVendorName() {
		Assert.assertTrue(dp.checkVendorStartWith());
	}

}
