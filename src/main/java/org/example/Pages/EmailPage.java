package org.example.Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import java.time.Duration;

public class EmailPage {
    private AndroidDriver driver;
    private WebDriverWait wait;
    private String Email;


    public EmailPage(AppiumDriver driver, WebDriverWait wait) {
        this.driver = (AndroidDriver) driver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void inputEmailAndContinue(String email) throws InterruptedException {
        for (char ch : email.toCharArray()) {
            if (ch == '@') {
                driver.pressKey(new KeyEvent(AndroidKey.AT));
            } else if (ch == '.') {
                driver.pressKey(new KeyEvent(AndroidKey.PERIOD));
            } else {
                driver.pressKey(new KeyEvent(AndroidKey.valueOf(Character.toString(ch).toUpperCase())));
            }
        }
        Thread.sleep(1000);
        WebElement continueButton = driver.findElement(By.xpath("//android.widget.Button[@text=\"Continue\"]"));
        continueButton.click();
        Thread.sleep(10000);
    }
}
