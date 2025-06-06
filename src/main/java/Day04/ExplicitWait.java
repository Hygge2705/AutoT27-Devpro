package Day04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWait {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Selenium sẽ đợi tối đa thời gian được chỉ định, nếu chưa thấy phần tử ngay.
        //Nếu thấy sớm hơn thì Selenium sẽ tiếp tục chạy mà không đợi đủ thời gian
        try {
            driver.get("https://saucelabs.com/request-demo");

            //Tạo WebDriverWait
            WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(3));

            //Chờ và Nhập email
            WebElement findEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
            findEmail.sendKeys("thomthom@businessEmail.com");

            //Chờ và Click nút Let's Talk
            WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.className("mktoButton")));
            submit.click();

        } catch (Exception e) {
            System.out.println("Lỗi tìm phần tử hoặc thao tác " + e.getMessage());
        }finally {
            driver.quit();
        }
    }
}
