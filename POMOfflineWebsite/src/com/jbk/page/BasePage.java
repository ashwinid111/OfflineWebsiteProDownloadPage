package com.jbk.page;

import java.util.List;

import org.openqa.selenium.WebElement;

public class BasePage {
	
	public void pause(int timetoSleep){
		try {
			Thread.sleep(timetoSleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	int sizeOfWebElementList(List<WebElement> list){
		return list.size();
	}

}
