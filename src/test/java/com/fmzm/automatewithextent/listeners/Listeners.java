package com.fmzm.automatewithextent.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fmzm.automatewithextent.extentReports.ExtentReportNG;
import com.fmzm.automatewithextent.testscripts.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends TestBase implements ITestListener {
    ExtentReports extent = ExtentReportNG.getReportObject();
    ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        WebDriver driver=null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass()
                    .getField("driver").get(result.getInstance());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        String testcaseName = result.getMethod().getMethodName();
        try {
            String screenshotPath = getScreenShot(testcaseName, driver);
            test.addScreenCaptureFromPath(screenshotPath, testcaseName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
