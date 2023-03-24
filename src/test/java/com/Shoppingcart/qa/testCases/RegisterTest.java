package com.Shoppingcart.qa.testCases;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shoppingcart.qa.Base.BaseClass;
import com.shoppingcart.qa.pageobjects.HomePage;
import com.shoppingcart.qa.pageobjects.RegistrationPage;
import com.shoppingcart.qa.utilities.DataUtil;
import com.shoppingcart.qa.utilities.Log;
import com.shoppingcart.qa.utilities.MyXLSReader;

public class RegisterTest extends BaseClass {

	public WebDriver driver;
	private MyXLSReader excelReader;
	private HomePage hp;
	private RegistrationPage rp;
	WebDriverWait wait;
	@BeforeMethod
	public void setUp() {
		driver = launchApp();
		hp = new HomePage(driver);
		hp.clickmyacc();
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	@Test(dataProvider = "getTestData")
	public void TC01_VerfiyNewRegister(HashMap<String, String> mapData) throws InterruptedException 
	{
		Log.startTestCase("TC01_VerfiyNewRegister");
		if (!DataUtil.isRunnable(excelReader, "RegisterTest", "testcases") || mapData.get("Runmode").equals("N")) {
			throw new SkipException("Run mode is set to N, hence not executed");
		}
		rp = hp.clickregister();
		rp.registerNewUser(mapData.get("firstname"), mapData.get("lastname"), mapData.get("email")+randomstring()+"@gmail.com",
						   mapData.get("telephone"), mapData.get("password"), mapData.get("confirmpassword"));
		//Thread.sleep(3);
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(rp.waitTo()));
		captureScreenshot("TC01_VerfiyNewRegister",driver);
		Log.info("New account created successfully");
		Assert.assertEquals(rp.accountCreated(), driver.getTitle(), "Registring Successful");
		rp.continueAfterRegister();
		Assert.assertTrue(rp.newMyaccountWindow().contains("Edit your account information"));
		Log.endTestCase("TC01_VerfiyNewRegister");
	}
	
	@DataProvider
	public Object[][] getTestData() {
		Object data[][] = null;
		String path = "./TestData/TestData.xlsx";
		try {
			excelReader = new MyXLSReader(path);
			data = DataUtil.getTestData(excelReader, "RegisterTest", "register");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return data;
	}

}
