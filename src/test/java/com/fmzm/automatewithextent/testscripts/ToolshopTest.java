package com.fmzm.automatewithextent.testscripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fmzm.automatewithextent.pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ToolshopTest extends TestBase {

    @Test
    public void verifySignInButton() {
        HomePage homePage = new HomePage(getDriver());
        homePage.get();
        assertThat(homePage.isSignInButtonDisplayed()).isTrue();
    }

    @Test
    public void verifySignInButton_failOnPurpose() {
        HomePage homePage = new HomePage(getDriver());
        homePage.get();
        assertThat(homePage.isSignInButtonDisplayed()).isFalse();
    }
}
