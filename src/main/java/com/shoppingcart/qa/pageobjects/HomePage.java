package com.shoppingcart.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;



public class HomePage {


	WebDriver driver;

	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(how=How.XPATH,using="//a[@title='My Account']")
	WebElement myaccount;
	@FindBy(how=How.XPATH,using="//a[contains(text(),('Register'))]")
	WebElement register;
	@FindBy(how=How.XPATH,using="//a[contains(text(),('Login'))]")
	WebElement login;

	public void clickmyacc()
	{
		myaccount.click();

	}
	public RegistrationPage clickregister()
	{
		register.click();
		return new RegistrationPage(driver);
	}
	public void clicklogin()
	{
		login.click();

	}
}
