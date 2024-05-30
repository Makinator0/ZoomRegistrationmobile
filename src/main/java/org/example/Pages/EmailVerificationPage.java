package org.example.Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.AndroidKey;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EmailVerificationPage {
    private static AndroidDriver driver;
    private WebDriverWait wait;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public EmailVerificationPage(AppiumDriver driver, WebDriverWait wait) {
        this.driver = (AndroidDriver) driver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static void inputDigits(String digits) {
        for (char digit : digits.toCharArray()) {
            if (Character.isDigit(digit)) {
                driver.pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + digit)));
            }
        }
    }

    public String getVerificationCode(User user) throws Exception {
        Thread.sleep(5000);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://privatix-temp-mail-v1.p.rapidapi.com/request/mail/id/" + user.getEmailHash() + "/"))
                .header("x-rapidapi-key", "63fbf2eb60msh3ad63b25eb7a109p15cee0jsn782da0ed0c5a")
                .header("x-rapidapi-host", "privatix-temp-mail-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JSONArray emails = new JSONArray(response.body());
        JSONObject email = emails.getJSONObject(0);
        String mailSubject = email.getString("mail_subject");

        return mailSubject.replaceAll("\\D", "");
    }


    public static void enterVerificationCode(String verificationCode) throws InterruptedException {
        inputDigits(verificationCode);
        WebElement verifyButton = driver.findElement(By.xpath("//android.widget.Button[@text=\"Verify\"]"));
        verifyButton.click();
        Thread.sleep(10000);
    }
}
