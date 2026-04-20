package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DriverFactory;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @Test
    public void validLoginTest() {
        loginPage.login("tomsmith", "SuperSecretPassword!");

        String message = loginPage.getMessage();
        Assert.assertTrue(message.contains("You logged into a secure area"));
    }

    @Test
    public void invalidLoginTest() {
        loginPage.login("wrong", "wrong");

        String message = loginPage.getMessage();
        Assert.assertTrue(message.contains("Your username is invalid"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}