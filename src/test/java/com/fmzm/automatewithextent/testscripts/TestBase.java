package com.fmzm.automatewithextent.testscripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.testsmith.support.listeners.*;
import com.fmzm.automatewithextent.utils.BrowserUtil;
import com.fmzm.automatewithextent.utils.ScreenshotListener;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import javax.activation.FileDataSource;
import java.io.File;
import java.io.IOException;

@Listeners(ScreenshotListener.class)
public abstract class TestBase {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

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

    @AfterClass
    public void teardown() {
        getDriver().quit();
    }

    public String getScreenShot(String testcaseName, WebDriver driver) throws IOException {
        TakesScreenshot tss = (TakesScreenshot) driver;
        File source = tss.getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
        File destination = new File(filePath);
        FileUtils.copyFile(source, destination);

        return filePath;
    }
}
