package com.shoppingcart.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;



public class RegistrationPage  {

	public WebDriver driver;
	public RegistrationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(how=How.XPATH,using="//input[@name='firstname']")
	private WebElement firstname;
	@FindBy(how=How.XPATH,using="//input[@name='lastname']")
	private WebElement lastname;
	@FindBy(how=How.XPATH,using="//input[@name='email']")
	private WebElement email1;
	@FindBy(how=How.XPATH,using="//input[@name='telephone']")
	private WebElement telephone;
	@FindBy(how=How.XPATH,using="//input[@name='password']")
	private WebElement password;
	@FindBy(how=How.XPATH,using="//input[@name='confirm']")
	private WebElement confirmpassword;
	@FindBy(how=How.XPATH,using="//input[@type='checkbox']")
	private WebElement agree;
	@FindBy(how=How.XPATH,using="//input[@type='submit']")
	private WebElement submit;
	@FindBy(how=How.XPATH,using="//div[@id='content']/h1")
	WebElement accountcreated;
	@FindBy(linkText="Continue")
	private WebElement continuepostsuccess;
	@FindBy(linkText="Edit your account information")
	private WebElement newmysccountwindow;
	@FindBy(how=How.XPATH,using="//div[@class='list-group'] //a[contains(text(),('Newsletter'))]")
	private WebElement newsletterrightcolumn;
	@FindBy(how=How.XPATH,using="//div[@class='col-sm-10']/label[2]/input]")
	private WebElement newsletterradiobtn;

	
	public void registerNewUser(String fname, String lname, String email, String tele, String pass,
			String cpass) {
		// TODO Auto-generated method stub
		firstname.sendKeys(fname);
		lastname.sendKeys(lname);
		email1.sendKeys(email);
		telephone.sendKeys(tele);
		password.sendKeys(pass);
		confirmpassword.sendKeys(cpass);
		agree.click();
		submit.click();
	}

	public WebElement waitTo() {
		return accountcreated;
	}
	public String accountCreated() {
		return accountcreated.getText();
	}
	
	public void continueAfterRegister()
	{
		
		 continuepostsuccess.click();
	}
	public String newMyaccountWindow() {
		return newmysccountwindow.getText();
	}
	
	public void newsletterrightcolumn()
	{
		newsletterrightcolumn.click();
	}
	public boolean newsletterradiobtn()
	{
		return newsletterrightcolumn.isSelected();
	}
	
}
