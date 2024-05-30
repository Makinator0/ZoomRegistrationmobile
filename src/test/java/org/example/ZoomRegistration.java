package org.example;

import org.example.Pages.EmailPage;
import org.example.Pages.EmailVerificationPage;
import org.example.Pages.FinalPage;
import org.example.Pages.MainPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URI;
import java.net.URL;

public class ZoomRegistration{
    private AndroidDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private EmailPage emailPage;

    private User user;

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "POCOPHONE F1");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("appPackage", "com.android.chrome");
        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");

        URL url = URI.create("http://127.0.0.1:4723/").toURL();
        driver = new AndroidDriver(url, capabilities);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        mainPage = new MainPage(driver, wait);
        emailPage = new EmailPage(driver, wait);
        UserFactory userFactory = new UserFactory();
        user = userFactory.createUser();


    }
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test(priority = 1)
    public void testEnterBirthYear() throws InterruptedException {
        mainPage.navigateMainPage();
        mainPage.closebutton();
        mainPage.enterBirthYear(user.getBirthYear());
    }
    @Test(priority = 2, dependsOnMethods = "testEnterBirthYear")
    public void testGetTemporaryEmail() throws Exception {
        emailPage.inputEmailAndContinue(user.getEmail());
        Assert.assertNotNull(user.getEmail(), "Temporary email should be generated");
        Assert.assertNotNull(user.getEmailHash(), "Email hash should be generated");

    }
    @Test(priority = 3, dependsOnMethods = "testGetTemporaryEmail")
    public void testRegisterOnZoom() throws Exception {
        EmailVerificationPage emailVerificationPage = new EmailVerificationPage(driver, wait);
        String verificationCode = emailVerificationPage.getVerificationCode(user);
        emailVerificationPage.enterVerificationCode(verificationCode);
    }
    @Test(priority = 4, dependsOnMethods = "testRegisterOnZoom")
    public void fillRegistrationDetails() throws Exception {
        FinalPage finalPage = new FinalPage(driver, wait);
        finalPage.inputFirstName(user.getFirstName());
        finalPage.inputLastName(user.getLastName());
        finalPage.inputPassword(user.getPassword());
        finalPage.clickContinue();
        Thread.sleep(1000);
    }
}
