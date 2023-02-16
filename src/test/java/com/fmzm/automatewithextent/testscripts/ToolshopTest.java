package com.fmzm.automatewithextent.testscripts;

import com.fmzm.automatewithextent.pages.HomePage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class ToolshopTest extends TestBase {

    public void verifySignInButton() {
        HomePage homePage = new HomePage(getDriver());
        homePage.get();
        assertThat(homePage.isSignInButtonDisplayed()).isTrue();
    }

    public void verifySignInButton_failOnPurpose() {
        HomePage homePage = new HomePage(getDriver());
        homePage.get();
        assertThat(homePage.isSignInButtonDisplayed()).isFalse();
    }
}
