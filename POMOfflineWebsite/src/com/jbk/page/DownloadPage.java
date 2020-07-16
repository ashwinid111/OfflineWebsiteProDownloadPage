package com.jbk.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DownloadPage {
	WebDriver wd = null;
	DownloadPage dp = null;

	public DownloadPage(WebDriver wd) {
		this.wd = wd;
		PageFactory.initElements(wd, this);
	}

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

	public boolean checkTotalRowColNum() {
		List<WebElement> listOfCol = tableColumn;
		System.out.println("num of column" + listOfCol.size());
		List<WebElement> listOfRows = tableRow;
		System.out.println("num of rows" + listOfRows.size());
		if (listOfCol.size() != listOfRows.size()) {
			return true;
		}
		return false;
	}

	public boolean validateHeaderCount() {
		List<WebElement> listOfHeader = header.findElements(By.tagName("th"));
		System.out.println("Total no of headers::" + listOfHeader.size());
		if (listOfHeader.size() == 8) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkFollowVender() {
		String actVendor = tableCell.getText();
		String expVendor = "Google Chrome";
		if (actVendor.equals(expVendor)) {
			System.out.println("Below Source Vendor.." + actVendor);
			return true;
		} else {
			return false;
		}

	}

	public boolean checkLink32bitClickable() {
		System.out.println("32bit link click()...");
		try {
			bit32Link.click();
			return true;
		} catch (Throwable t) {
			return false;
		}
	}

	public boolean checkLink64bitClickable() {
		System.out.println("64bit link click()...");
		try {
			bit64Link.click();
			return true;
		} catch (Throwable t) {
			System.out.println("exception");
			return false;
		}
	}

	public boolean checkOfficialWebsiteClickable() {
		System.out.println("Official Website link click()...");
		try {
			officialWebsiteLink.click();
			return true;
		} catch (Throwable t) {
			return false;
		}
	}

	public boolean checkVendorListIsSort() {
		ArrayList<String> actualWebList = new ArrayList<>();
		List<WebElement> elementList = tableVendorsList;
		for (WebElement we : elementList) {
			actualWebList.add(we.getText());
		}
		System.out.println(actualWebList);
		ArrayList<String> sortedList = new ArrayList<>();
		for (String s : actualWebList) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		if (sortedList.equals(actualWebList)) {
			System.out.println("Vendor List is sorted.." + sortedList);
			return true;
		} else {
			System.out.println("Vendor List is not sorted.." + sortedList);
			System.out.println(sortedList);
			return false;
		}
	}

	public boolean checkSrNumberListInSort() {
		ArrayList<String> actualSrNumList = new ArrayList<>();
		List<WebElement> elementList = tableSrNumbers;
		for (WebElement we : elementList) {
			actualSrNumList.add(we.getText());
		}
		System.out.println(actualSrNumList);
		ArrayList<String> sortedList = new ArrayList<>();
		for (String s : actualSrNumList) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		if (sortedList.equals(actualSrNumList)) {
			System.out.println("List is sorted" + sortedList);
			return true;
		} else {
			System.out.println("List is not sorted" + sortedList);
			return false;
		}
	}

	public boolean checkVendorStartWith() {

		ArrayList<String> actualVendorList = new ArrayList<>();
		List<WebElement> elementList = tableVendorsList;
		for (WebElement we : elementList) {
			actualVendorList.add(we.getText());
		}
		System.out.println(actualVendorList);
		ArrayList<String> matchList = new ArrayList<>();
		boolean status = true;

		for (String vendor : actualVendorList) {
			status = vendor.startsWith("Google");
			System.out.println(status);
			if (status == true) {
				System.out.println(" Vendor name Starts with Google..");
				matchList.add("status");
				System.out.println(matchList.size());
			}
		}
		return true;
	}
	/*
	 * ArrayList<String> actualWebList = new ArrayList<>(); ArrayList<String>
	 * sortedList = new ArrayList<>(); public void checkSort(){
	 * //ArrayList<String> actualWebList = new ArrayList<>(); List<WebElement>
	 * elementList = tableVendorsList; for (WebElement we : elementList) {
	 * actualWebList.add(we.getText()); } System.out.println(actualWebList);
	 * //ArrayList<String> sortedList = new ArrayList<>(); for (String s :
	 * actualWebList) { sortedList.add(s); } Collections.sort(sortedList);
	 * System.out.println(sortedList);
	 * 
	 * }
	 */
}