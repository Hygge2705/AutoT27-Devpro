package Day03.HW_DAY03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HW_Day03_Case01 {
    static WebDriver driver = null;

    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        System.out.println("Title: " + driver.getTitle());

        // Nhập email và chờ các trường khác xuất hiện
        WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
        inputEmail.sendKeys("thomthom@businessEmail.com");

        // Chờ First Name hiển thị
        WebElement inputFirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName")));
        inputFirstName.sendKeys("thom");

        WebElement inputLastName = driver.findElement(By.id("LastName"));
        inputLastName.sendKeys("thom");

        WebElement inputCompany = driver.findElement(By.id("Company"));
        inputCompany.sendKeys("Test Company");

        WebElement inputPhone = driver.findElement(By.id("Phone"));
        inputPhone.sendKeys("0911888999");

        // Chờ dropdown Country hiển thị
        WebElement inputCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Country")));
        Select optionCountry = new Select(inputCountry);
        optionCountry.selectByValue("Argentina");

        WebElement inputInterest = driver.findElement(By.id("Solution_Interest__c"));
        Select optionInterest = new Select(inputInterest);
        optionInterest.selectByValue("Mobile Application Testing");

        // Đồng ý điều khoản
        WebElement termsCheckbox = driver.findElement(By.id("LblmktoCheckbox_46340_0"));
        termsCheckbox.click();

        // Click nút Let's Talk
        WebElement submit = driver.findElement(By.className("mktoButton"));
        submit.click();

        driver.quit();
    }
}
