package com.jbk.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class DownloadPage   {
	WebDriver wd = null;
	DownloadPage dp = null;
	ExtentTest test1=null;
	public DownloadPage(WebDriver wd) {
		this.wd = wd;
		PageFactory.initElements(wd, this);
	}
	Logger logger=Logger.getLogger(this.getClass());

	@FindBy(id = "email")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(xpath = "//button")
	private WebElement loginButton;

	public void typeUserName(String uName) {
		username.sendKeys(uName);
	}

	public void typePassword(String pass) {
		password.sendKeys(pass);
	}

	@FindBy(xpath = "//span[text()='Downloads']")
	private WebElement downloads;

	@FindBy(xpath = "//tr//th")
	private List<WebElement> tableColumn;

	@FindBy(xpath = "//tr//td")
	private List<WebElement> tableRow;

	@FindBy(xpath = "//tr[1]")
	private WebElement header;

	@FindBy(xpath = "//tr[4]//td[3]//following::tr[1]//td[3]")
	private WebElement tableCell;

	@FindBy(xpath = "//span[text()='32bit']")
	private WebElement bit32Link;

	@FindBy(xpath = "//span[text()='32bit']")
	private WebElement bit64Link;

	@FindBy(xpath = "//span[text()='32bit']")
	private WebElement officialWebsiteLink;

	@FindBy(xpath = "//table[@class='table table-hover']//child::tr//td[3]")
	private List<WebElement> tableVendorsList;

	@FindBy(xpath = "//table[@class='table table-hover']//child::tr//td[1]")
	private List<WebElement> tableSrNumbers;

	public void navigateToDownloadPage() {
		typeUserName("kiran@gmail.com");
		typePassword("123456");
		loginButton.click();
		downloads.click();
	}

	public boolean checkTotalRowColNum(ExtentTest logger2) {
		
		logger2.log(Status.INFO, "Finding number of rows and columns:");
		List<WebElement> listOfCol = tableColumn;
		logger2.log(Status.INFO,"num of column" +listOfCol.size());
		List<WebElement> listOfRows = tableRow;
		logger2.log(Status.INFO,"num of rows" +listOfRows.size());
		if (listOfCol.size() != listOfRows.size()) {
			logger.info("The size of rows and coloumns not same.");
			return true;
		}
		return false;
	}

	public boolean validateHeaderCount(ExtentTest logger2) {
		//test1.log(Status.PASS, "asd");
		logger2.log(Status.INFO, "Finding list of headers");
		List<WebElement> listOfHeader = header.findElements(By.tagName("th"));
		logger2.log(Status.INFO, "Header list finding completed ");
		logger2.log(Status.INFO,"Total no of headers::" +listOfHeader.size());
		
		if (listOfHeader.size()==8) {
			logger2.log(Status.PASS, "Header size is matched");
			return true;
		} else {
			logger2.log(Status.FAIL, "Header size is not matched");
			return false;
		}
	}

	public boolean checkFollowVender(ExtentTest logger2) {
		logger2.log(Status.INFO, "Checking Following Vendor of Selenium ");
		String actVendor = tableCell.getText();
		logger2.log(Status.INFO, "Following Vendor of Selenium "+actVendor);
		String expVendor = "Google Chrome";
		logger2.log(Status.INFO, "Expected Following Vendor of Selenium is"+expVendor);
		if (actVendor.equals(expVendor)) {
			logger2.log(Status.PASS,"Source Vendor match with expected vendor.." + actVendor);
			return true;
		} else {
			logger2.log(Status.FAIL,"Source Vendor not match with expected vendor.." + actVendor);
			return false;
		}

	}

	public boolean checkLink32bitClickable(ExtentTest logger2) {
		logger2.log(Status.INFO, "Validate 32bit link click or not");
		try {
			bit32Link.click();
			logger2.log(Status.PASS,"32bit link is clickable");
			return true;
		} catch (Throwable t) {
			logger2.log(Status.FAIL,"32bit link is not clickable");
			return false;
		}
	}

	public boolean checkLink64bitClickable(ExtentTest logger2) {
		logger2.log(Status.INFO, "Validate 64bit link click or not");
		try {
			bit64Link.click();
			logger2.log(Status.PASS,"64bit link is clickable");
			return true;
		} catch (Throwable t) {
			logger2.log(Status.FAIL,"64Bit link not click suceefully..");
			return false;
		}
	}

	public boolean checkOfficialWebsiteClickable(ExtentTest logger2) {
		logger2.log(Status.INFO,"Official Website link click()...");
		try {
			officialWebsiteLink.click();
			logger2.log(Status.PASS,"Official Website link click..");
			return true;
		} catch (Throwable t) {
			logger2.log(Status.FAIL,"Official Website link not click suceefully..");
			return false;
		}
	}

	public boolean checkVendorListIsSort(ExtentTest logger2) {
		logger2.log(Status.INFO,"Validate Is vendor name list sorted");
		ArrayList<String> actualWebList = new ArrayList<>();
		List<WebElement> elementList = tableVendorsList;
		logger2.log(Status.INFO,"Actul list of Vendor Sequence..");
		for (WebElement we : elementList) {
			actualWebList.add(we.getText());
		}
		logger2.log(Status.INFO,"Actul list of Vendor Name.."+actualWebList);
		ArrayList<String> sortedList = new ArrayList<>();
		for (String s : actualWebList) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		logger2.log(Status.INFO,"Sorted actul list of Vendor Name..");
		if (sortedList.equals(actualWebList)) {
			logger2.log(Status.PASS,"Vendor List is sorted.." + sortedList);
			return true;
		} else {
			logger2.log(Status.FAIL,"Vendor unsorted List after sorting.." + sortedList);
			logger.debug(sortedList);
			return false;
		}
	}

	public boolean checkSrNumberListInSort(ExtentTest logger2) {
		logger2.log(Status.INFO,"Validate Is Sr. numbers list sorted");
		ArrayList<String> actualSrNumList = new ArrayList<>();
		List<WebElement> elementList = tableSrNumbers;
		logger2.log(Status.INFO,"Actul list of Sr. numbers Sequence..");
		for (WebElement we : elementList) {
			actualSrNumList.add(we.getText());
		}
		logger.debug(actualSrNumList);
		logger2.log(Status.INFO,"Actul list of Sr. numbers.."+actualSrNumList);
		ArrayList<String> sortedList = new ArrayList<>();
		for (String s : actualSrNumList) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		logger2.log(Status.INFO,"Sorted actul list of Vendor Name..");
		if (sortedList.equals(actualSrNumList)) {
			logger2.log(Status.PASS,"List is sorted" + sortedList);
			return true;
		} else {
			logger2.log(Status.FAIL,"List is not sorted" + sortedList);
			return false;
		}
	}

	public boolean checkVendorStartWith(ExtentTest logger2) {
		logger2.log(Status.INFO,"Validate Is vendor name start with Google");
		ArrayList<String> actualVendorList = new ArrayList<>();
		List<WebElement> elementList = tableVendorsList;
		logger2.log(Status.INFO,"Actul list of Vendors..");
		for (WebElement we : elementList) {
			actualVendorList.add(we.getText());
		}
		logger.debug(actualVendorList);
		logger2.log(Status.INFO,"Actul list of Sr. numbers.."+actualVendorList);
		ArrayList<String> matchList = new ArrayList<>();
		boolean status = true;

		for (String vendor : actualVendorList) {
			status = vendor.startsWith("Google");
			logger.debug(status);
			if (status == true) {
				logger2.log(Status.PASS," Vendor name Starts with Google..");
				matchList.add("status");
				logger2.log(Status.INFO,"Size of vendors list."+matchList.size());
			}
		}
		return true;
	}
}
