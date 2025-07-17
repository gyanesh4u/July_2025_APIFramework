package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener {

	ExtentSparkReporter spark;// user interface/ look & feel of the report
	ExtentReports extent;// common information
	ExtentTest test;// entries for test
	String timestamp;

	public void configureReport() {
		 timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		spark = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp + ".html");

		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("API Test Report");
		spark.config().setReportName("Automation Results");

		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("OS", "macOS");
		extent.setSystemInfo("Tester", "Gyanesh");
	}

	// OnStart method is called when any Test starts.
	public void onStart(ITestContext Result) {
		configureReport();
		System.out.println("On Start method invoked....");
	}

	// onFinish method is called after all Tests are executed
	public void onFinish(ITestContext Result) {
		System.out.println("On Finished method invoked....");
		System.out.println(
				"Extent Report saved at: " + System.getProperty("user.dir") + "/test-output/ExtentReport.html");

		extent.flush();// it is mandatory to call flush method to ensure information is written to the
						// started reporter.
		// At the end of onFinish()
		try {
		    File latestReport = new File(System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp + ".html");
		    File jenkinsReport = new File(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
		    Files.copy(latestReport.toPath(), jenkinsReport.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
		    e.printStackTrace();
		}


	}

	// When Test case get failed, this method is called.

	public void onTestFailure(ITestResult Result) {
		System.out.println("Name of test method failed:" + Result.getName());
		test = extent.createTest(Result.getName());// create entry in html report
		test.log(Status.FAIL,
				MarkupHelper.createLabel("Name of the failed test case is: " + Result.getName(), ExtentColor.RED));

		String screenShotPath = System.getProperty("user.dir") + "\\ScreenShots\\" + Result.getName() + ".png";

		File screenShotFile = new File(screenShotPath);

		if (screenShotFile.exists()) {
			test.fail("Captured Screenshot is below:" + test.addScreenCaptureFromPath(screenShotPath));

		}

		// test.addScreenCaptureFromPath(null)

	}

	// When Test case get Skipped, this method is called.

	public void onTestSkipped(ITestResult Result) {
		System.out.println("Name of test method skipped:" + Result.getName());

		test = extent.createTest(Result.getName());
		test.log(Status.SKIP,
				MarkupHelper.createLabel("Name of the skip test case is: " + Result.getName(), ExtentColor.YELLOW));
	}

	// When Test case get Started, this method is called.

	public void onTestStart(ITestResult Result) {
		System.out.println("Name of test method started:" + Result.getName());

	}

	// When Test case get passed, this method is called.

	public void onTestSuccess(ITestResult Result) {
		System.out.println("Name of test method sucessfully executed:" + Result.getName());

		test = extent.createTest(Result.getName());
		test.log(Status.PASS,
				MarkupHelper.createLabel("Name of the passed test case is: " + Result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {

	}

}
