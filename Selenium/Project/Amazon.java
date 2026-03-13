package Selenium;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Amazon {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.amazon.in");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox"))
        );

        searchBox.sendKeys("Iphone 17 Pro");

      
        driver.findElement(By.id("nav-search-submit-button")).click();

        WebElement price = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//span[@class='a-price-whole'])[3]")
                )
        );

        System.out.println("Price of 3rd result: " + price.getText());

        driver.quit();
    }
}