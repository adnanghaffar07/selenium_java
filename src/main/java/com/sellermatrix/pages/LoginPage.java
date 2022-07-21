package com.sellermatrix.pages;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sellermatrix.base.BaseClass;
import com.sellermatrix.base.PropertiesReader;

public class LoginPage extends BaseClass {

	@FindBy(xpath = ("(//*[contains(text(),'Login')])[last()]"))
	WebElement btnLogin;

	@FindBy(xpath = ("//*[contains(text(),'Dashboard')]"))
	WebElement userDashboard;
	
	@FindBy(xpath = ("//input[@name='email']"))
	WebElement inputName;
	
	@FindBy(xpath = ("//input[@name='password']"))
	WebElement inputPass;

	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnLoginButton() {
		click(btnLogin);
	}
	
	public void enterEmail(String email) {
		type(inputName, email);
	}
	
	public void enterPassword(String pass) {
		type(inputPass, pass);
	}
	
	public boolean isUserDashboardDisplaying() {
		return isElementDisplayed(userDashboard);
	}
	
	public boolean isDisplayingOnUserDashboard(String value) {
		return isElementDisplayed(driver.findElement(By.xpath("//*[contains(text(),'"+value+"')]")));
	}
	
}
