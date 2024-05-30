package org.example.Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FinalPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public FinalPage(WebDriver driver, WebDriverWait wait) {
        this.driver = (AndroidDriver) driver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    public void inputFirstName(String firstName)  {
        for (char ch : firstName.toCharArray()) {
                if (ch == '@') {
                    driver.pressKey(new KeyEvent(AndroidKey.AT));
                } else if (ch == '.') {
                    driver.pressKey(new KeyEvent(AndroidKey.PERIOD));
                } else {
                    driver.pressKey(new KeyEvent(AndroidKey.valueOf(Character.toString(ch).toUpperCase())));
                }
        }
        driver.pressKey(new KeyEvent(AndroidKey.TAB));

    }

    public void inputLastName(String lastName) {
        for (char ch : lastName.toCharArray()) {
            if (ch == '@') {
                driver.pressKey(new KeyEvent(AndroidKey.AT));
            } else if (ch == '.') {
                driver.pressKey(new KeyEvent(AndroidKey.PERIOD));
            } else {
                driver.pressKey(new KeyEvent(AndroidKey.valueOf(Character.toString(ch).toUpperCase())));
            }
        }
        driver.pressKey(new KeyEvent(AndroidKey.TAB));
    }

    public void inputPassword(String password) {
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                driver.pressKey(new KeyEvent(AndroidKey.SHIFT_LEFT));
                driver.pressKey(new KeyEvent(AndroidKey.valueOf(String.valueOf(ch))));
            } else if (Character.isLowerCase(ch)) {
                driver.pressKey(new KeyEvent(AndroidKey.valueOf(String.valueOf(Character.toUpperCase(ch)))));
            } else if (Character.isDigit(ch)) {
                driver.pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + ch)));
            } else {  driver.pressKey(new KeyEvent(AndroidKey.AT));
            }
            driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        }
    }

    public void clickContinue() throws InterruptedException {
        WebElement continueButton = driver.findElement(By.xpath("//android.widget.Button[@text=\"Continue\"]"));
        continueButton.click();
        Thread.sleep(7000);
    }
}
