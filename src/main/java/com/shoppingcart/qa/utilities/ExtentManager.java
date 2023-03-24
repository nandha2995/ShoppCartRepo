package com.shoppingcart.qa.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.shoppingcart.qa.Base.BaseClass;


public class ExtentManager {

	public static ExtentReports reporter;
	public static ExtentSparkReporter spark;
	static String Reportpath;

	public static ExtentReports generateExtentreport()  {
		
		//Date date = new Date();
		//String randomString = date.toString().replace(" ", "_").replace(":", "_");
		reporter = new ExtentReports();		
		try {
			Reportpath = System.getProperty("user.dir") + "\\Reports\\"+"ExtentReport_"+BaseClass.randomstring()+".html";			
		} 	catch(Throwable e)
		{
			e.printStackTrace();
		}
		spark = new ExtentSparkReporter(Reportpath);
        spark.config().setReportName("TurorialsNinjaShoppingApp");
		spark.config().setDocumentTitle("TutorialsNinjaQAReport");
		spark.config().setTheme(Theme.DARK);
		reporter.attachReporter(spark);
		reporter.setSystemInfo("QATester", "Nandha");
		return reporter;
	}
}
