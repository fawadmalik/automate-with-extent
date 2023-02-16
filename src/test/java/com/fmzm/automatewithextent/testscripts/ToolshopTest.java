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
        ExtentTest test = extent.createTest("verifySignInButton");
        HomePage homePage = new HomePage(getDriver());
        homePage.get();
        try {
            assertThat(homePage.isSignInButtonDisplayed()).isTrue();
            test.pass("homePage.isSignInButtonDisplayed()).isTrue()");
        }
        catch (AssertionError ae){
            test.fail(ae.getMessage());
        }

    }

    @Test
    public void verifySignInButton_failOnPurpose() {
        ExtentTest test = extent.createTest("verifySignInButton_failOnPurpose");
        HomePage homePage = new HomePage(getDriver());
        homePage.get();

        try {
            assertThat(homePage.isSignInButtonDisplayed()).isFalse();
            test.pass("homePage.isSignInButtonDisplayed()).isFalse()");
        }
        catch (AssertionError ae){
            test.fail(ae.getMessage());
        }

    }
}
