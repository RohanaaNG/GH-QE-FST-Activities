package Appium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class SimpleActivity {

    // Test method with the requested code snippet
    @Test
    public void simpleChromeTest() throws MalformedURLException, URISyntaxException {
        AndroidDriver driver;

        // Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");

        // Server Address
        URL serverURL = new URI("http://localhost:4723").toURL();
        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);
        // Open the browser with the URL
        driver.get("https://training-support.net");

        // Locate heading on page and print it
        String pageTitle = driver.findElement(AppiumBy.xpath("//*[contains(text(),'Training')]")).getText();
        System.out.println("Heading: " + pageTitle);

        // Close the driver
        driver.quit();
    }
}