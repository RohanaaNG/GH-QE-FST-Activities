package TestNg;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

public class Orange {

    WebDriver driver;
    WebDriverWait wait;

    String url = "https://alchemy.hguy.co/orangehrm/";
    String username = "orange";
    String password = "orangepassword123";

    String empId;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterClass
    public void close() {
        driver.quit();
    }

    // 1️⃣ Verify Title
    @Test(priority = 1)
    public void verifyTitle() {
        driver.get(url);
        System.out.println("Title: " + driver.getTitle());
    }

    // 2️⃣ Get Logo URL
    @Test(priority = 2)
    public void getLogo() {
        driver.get(url);
        WebElement logo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='divLogo']//img")));
        System.out.println("Logo URL: " + logo.getAttribute("src"));
    }

    // 3️⃣ Login
    @Test(priority = 3)
    public void loginTest() {
        driver.get(url);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")))
            .sendKeys(username);

        driver.findElement(By.id("txtPassword")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();

        System.out.println(" Login Done");
    }

    // 4️⃣ Add Employee
    @Test(priority = 4)
    public void addEmployee() {

        String fname = "John";
        String lname = "Doe" + UUID.randomUUID().toString().substring(0, 4);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewPimModule"))).click();
        driver.findElement(By.id("menu_pim_addEmployee")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).sendKeys(fname);
        driver.findElement(By.id("lastName")).sendKeys(lname);

        empId = driver.findElement(By.id("employeeId")).getAttribute("value");

        driver.findElement(By.id("btnSave")).click();

        System.out.println("Employee Added: " + empId);
    }

    // 5️⃣ Edit Personal Info
    @Test(priority = 5)
    public void editInfo() {

        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewMyDetails"))).click();

        driver.findElement(By.id("btnSave")).click();

        WebElement fname = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("personal_txtEmpFirstName")));
        fname.clear();
        fname.sendKeys("Rohan");

        driver.findElement(By.id("personal_txtEmpLastName")).clear();
        driver.findElement(By.id("personal_txtEmpLastName")).sendKeys("Kumar");

        driver.findElement(By.id("btnSave")).click();

        System.out.println(" Personal Info Updated");
    }

    // 6️⃣ Directory
    @Test(priority = 6)
    public void directory() {

        WebElement dir = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("menu_directory_viewDirectory")));

        dir.click();

        System.out.println("Directory Opened");
    }

    // 7️⃣ Add Qualification
    @Test(priority = 7)
    public void addQualification() {

        driver.findElement(By.id("menu_pim_viewMyDetails")).click();
        driver.findElement(By.linkText("Qualifications")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("addWorkExperience"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("experience_employer")))
            .sendKeys("ABC Company");

        driver.findElement(By.id("experience_jobtitle")).sendKeys("Tester");
        driver.findElement(By.id("experience_from_date")).sendKeys("2022-01-01");
        driver.findElement(By.id("experience_to_date")).sendKeys("2023-01-01");
        driver.findElement(By.id("experience_comments")).sendKeys("Testing");

        driver.findElement(By.id("btnWorkExpSave")).click();

        System.out.println("Qualification Added");
    }

    // 8️⃣ Apply Leave
    @Test(priority = 8)
    public void applyLeave() {

        driver.findElement(By.id("menu_leave_viewLeaveModule")).click();
        driver.findElement(By.id("menu_leave_applyLeave")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("applyleave_txtFromDate")))
            .sendKeys("2026-03-25");

        driver.findElement(By.id("applyleave_txtToDate")).sendKeys("2026-03-26");
        driver.findElement(By.id("applyleave_txtComment")).sendKeys("Personal");

        driver.findElement(By.id("applyBtn")).click();

        System.out.println(" Leave Applied");
    }

    // 9️⃣ Emergency Contacts
    @Test(priority = 9)
    public void emergencyContacts() {

        driver.findElement(By.id("menu_pim_viewMyDetails")).click();
        driver.findElement(By.linkText("Emergency Contacts")).click();

        List<WebElement> rows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table/tbody/tr")));

        System.out.println("Emergency Contacts:");

        for (WebElement row : rows) {
            System.out.println(row.getText());
        }
    }

    // 🔟 Final
    @Test(priority = 10)
    public void done() {
        System.out.println("All tasks completed successfully");
    }
}