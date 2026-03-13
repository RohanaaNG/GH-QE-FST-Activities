package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ACtivity6 {
    public static void main(String[] args) {
        WebDriver driver=new FirefoxDriver();
        driver.get("https://training-support.net/webelements/dynamic-controls");
        System.out.println("THE tittle of page is ..."+ driver.getTitle());


        WebElement checkbox=driver.findElement(By.id("checkbox"));
        checkbox.click();
         System.out.println("Checkbox is selected: " + checkbox.isSelected());
         checkbox.click();
         System.out.println("Checkbox is selected: " + checkbox.isSelected());
         checkbox.click();
         


    }}