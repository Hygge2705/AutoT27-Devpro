package Day06.HW_Day06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class BookDemoForm_TestNG {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void InputWithDataIsValid(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://saucelabs.com/request-demo");

        // Kiểm tra tiêu đề và URL
        Assert.assertEquals(driver.getTitle(), "Request a Sauce Labs Demo", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/request-demo", "URL mismatch!");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email"))).sendKeys("thomthom@businessEmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName"))).sendKeys("thom");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LastName"))).sendKeys("thom");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Company"))).sendKeys("Test Company");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Phone"))).sendKeys("0911888999");
        // Chờ dropdown Country hiển thị
        WebElement inputCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Country")));
        Select optionCountry = new Select(inputCountry);
        optionCountry.selectByValue("Argentina");

        WebElement inputInterest = driver.findElement(By.id("Solution_Interest__c"));
        Select optionInterest = new Select(inputInterest);
        optionInterest.selectByValue("Mobile Application Testing");

        // Đồng ý điều khoản
        driver.findElement(By.id("LblmktoCheckbox_46340_0")).click();

        // Click nút Let's Talk
        driver.findElement(By.className("mktoButton")).click();

        // Xác nhận vào gửi thành công
        Assert.assertTrue(wait.until(ExpectedConditions.urlContains("thank-you-contact")), "Should stay on book form!");
    }
    @Test
    public void InputWithEmailIsInvalid(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://saucelabs.com/request-demo");

        // Kiểm tra tiêu đề và URL
        Assert.assertEquals(driver.getTitle(), "Request a Sauce Labs Demo", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/request-demo", "URL mismatch!");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email"))).sendKeys("thomthom@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName"))).sendKeys("thom");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LastName"))).sendKeys("thom");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Company"))).sendKeys("Test Company");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Phone"))).sendKeys("0911888999");
        // Chờ dropdown Country hiển thị
        WebElement inputCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Country")));
        Select optionCountry = new Select(inputCountry);
        optionCountry.selectByValue("Argentina");

        WebElement inputInterest = driver.findElement(By.id("Solution_Interest__c"));
        Select optionInterest = new Select(inputInterest);
        optionInterest.selectByValue("Mobile Application Testing");

        // Đồng ý điều khoản
        driver.findElement(By.id("LblmktoCheckbox_46340_0")).click();

        // Click nút Let's Talk
        driver.findElement(By.className("mktoButton")).click();

        // Xác nhận vào gửi khong thành công
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/request-demo", "Send successfully!");
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ValidMsgEmail")));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed!");
        Assert.assertTrue(errorMessage.getText().contains("Enter valid business email address"), "Text of alert is incorrect!");

    }

    @AfterClass
        public void exit(){
            driver.quit();
    }
}
