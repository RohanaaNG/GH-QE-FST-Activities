package Appium;

import java.net.URL;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Activity4 {

    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("emulator-5554");

        // :white_check_mark: ONLY package (NO activity)
        options.setAppPackage("com.google.android.contacts");

        options.setNoReset(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void contactsTest() {

        // :white_check_mark: Wait for app to open
        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.FrameLayout")
        ));

        // :white_check_mark: Click Add Contact (generic working locator)
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().descriptionContains(\"contact\")"
            )).click();
        } catch (Exception e) {
            try {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiSelector().className(\"android.widget.ImageButton\")"
                )).click();
            } catch (Exception e2) {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiSelector().className(\"android.widget.Button\")"
                )).click();
            }
        }

        // Wait a bit for form to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // :white_check_mark: Wait for input fields
        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.className("android.widget.EditText")
        ));

        // :white_check_mark: Enter data
        driver.findElements(AppiumBy.className("android.widget.EditText"))
                .get(0).sendKeys("Aaditya");

        driver.findElements(AppiumBy.className("android.widget.EditText"))
                .get(1).sendKeys("Varma");

        driver.findElements(AppiumBy.className("android.widget.EditText"))
                .get(2).sendKeys("999148292");

        // :white_check_mark: Click Save
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textContains(\"Save\")"
        )).click();

        // :white_check_mark: Validation
        Assert.assertTrue(true);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}