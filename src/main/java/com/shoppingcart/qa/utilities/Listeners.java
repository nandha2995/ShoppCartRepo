package com.shoppingcart.qa.utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shoppingcart.qa.Base.BaseClass;

public class Listeners extends ExtentManager implements ITestListener {
	
	
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		
		try {
			reporter =generateExtentreport();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		extentTest = new ThreadLocal<ExtentTest>();
	}

	@Override
	public void onTestStart(ITestResult result) {
		
	test=reporter.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		if(result.getStatus()==ITestResult.SUCCESS) {
		extentTest.get().log(Status.PASS,
				MarkupHelper.createLabel(result.getMethod().getMethodName() + " - Test Case Passed is", ExtentColor.GREEN));
	}
	
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().log(Status.FAIL,
				MarkupHelper.createLabel(result.getMethod().getMethodName() + " - Test Case Failed is", ExtentColor.RED));
		extentTest.get().log(Status.FAIL,
				MarkupHelper.createLabel(result.getMethod().getMethodName() + " - Test Case Failed is", ExtentColor.RED));
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {

			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(BaseClass.captureScreenshot(result.getMethod().getMethodName(), driver),
				result.getMethod().getMethodName());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		if (result.getStatus() == ITestResult.SKIP) {
			extentTest.get().log(Status.SKIP,
					MarkupHelper.createLabel(result.getMethod().getMethodName() + " - Test Case Skipped is", ExtentColor.YELLOW));
		}
	}
	
	@Override
	public void onFinish(ITestContext context) {
		
		reporter.flush();
	
	}
	
}
