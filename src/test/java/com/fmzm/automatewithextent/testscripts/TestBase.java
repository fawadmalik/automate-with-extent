package com.fmzm.automatewithextent.testscripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.testsmith.support.listeners.*;
import com.fmzm.automatewithextent.utils.BrowserUtil;
import com.fmzm.automatewithextent.utils.ScreenshotListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(ScreenshotListener.class)
public abstract class TestBase {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    ExtentReports extent;

    @BeforeClass
    public void setup() {
        final String browser = System.getProperty("browser", "chrome");
        WebDriver originalDriver = BrowserUtil.createDriver(browser);
        driver.set(new EventFiringDecorator(
                new WebDriverLoggingListener(),
                new SavePageSourceOnExceptionListener(originalDriver, "target/log/pagesources"),
                new SaveScreenshotOnExceptionListener(originalDriver, "target/log/screenshots"),
                new HighlightElementsListener()
        ).decorate(originalDriver));
    }

    @BeforeClass
    public void configExtent(){
        //extentreports , extentsparkrepoprter
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);

        sparkReporter.config().setReportName("Web Automation Results");
        sparkReporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Tester", "Fawad Malik");
    }

    @AfterClass
    public void teardown() {
        getDriver().quit();
    }

    @AfterClass
    public void teardownExtent(){
        extent.flush();
    }

}
