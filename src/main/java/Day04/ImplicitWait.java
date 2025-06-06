package Day04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ImplicitWait {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Selenium sẽ đợi tối đa thời gian được chỉ định, nếu chưa thấy phần tử ngay.
        //Nếu thấy sớm hơn thì Selenium sẽ tiếp tục chạy mà không đợi đủ thời gian
        try {
            driver.get("https://saucelabs.com/request-demo");

            // Nhập email và chờ các trường khác xuất hiện
            WebElement findEmail = driver.findElement(By.id("Email"));
            findEmail.sendKeys("thomthom@businessEmail.com");

            // Click nút Let's Talk
            WebElement submit = driver.findElement(By.className("mktoButton"));
            submit.click();

        } catch (Exception e) {
            System.out.println("Lỗi tìm phần tử hoặc thao tác " + e.getMessage());
        }finally {
            driver.quit();
        }
    }
}
