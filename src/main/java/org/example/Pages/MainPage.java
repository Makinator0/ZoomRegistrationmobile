package org.example.Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public MainPage(AppiumDriver driver, WebDriverWait wait) {
        this.driver = (AndroidDriver) driver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void navigateMainPage() throws InterruptedException {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/signin_fre_continue_button")));
        continueButton.click();
        Thread.sleep(7000);

        WebElement skipButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/button_secondary")));
        skipButton.click();
        Thread.sleep(7000);

        WebElement allowButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/more_button")));
        allowButton.click();
        Thread.sleep(7000);

        WebElement urlField = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/ack_button")));
        urlField.click();
        Thread.sleep(7000);

        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/search_box_text")));
        inputField.click();

        WebElement urlBar = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/url_bar")));
        urlBar.sendKeys("https://zoom.us/signup#/signup");
        Thread.sleep(7000);

        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        Thread.sleep(7000);
    }
    public void closebutton() throws InterruptedException {
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text=\"Закрыть\"]")));
        closeButton.click();
    }


    public void enterBirthYear(String birthYear) throws InterruptedException {
        WebElement yearField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@text='Birth Year']")));
        yearField.click();

        for (char ch : birthYear.toCharArray()) {
            AndroidKey key = AndroidKey.valueOf("DIGIT_" + ch);
            driver.pressKey(new KeyEvent(key));
        }

        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        Thread.sleep(7000);
    }


}

