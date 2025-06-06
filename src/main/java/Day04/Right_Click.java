package Day04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Right_Click {static WebDriver driver = null;

    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        System.out.println("Title: " + driver.getTitle());

//  Thực hiện right click
        WebElement btnRightClick =driver.findElement(By.xpath("//span[text()='right click me']"));

//  Tạo đối tượng Actions để thực hiện right-click
        Actions action = new Actions(driver);

//  Thực hiện right-click vào button
        action.contextClick(btnRightClick).perform();

//  Lấy danh sách các tuỳ chọn trong menu chuột phải
        WebElement option = driver.findElement(By.xpath("//li/span[text()='Edit']"));

//  Nhấp vào tuỳ chọn "Edit"
        option.click();

//  Xử lý cảnh báo "alert" xuất hiện sau khi bouble click:

        String alertText = driver.switchTo().alert().getText();

        System.out.print("\nAlert text after right click: '"+alertText +"'");

//  Đóng alert
        driver.switchTo().alert().accept();

//  Đóng trình duyệt
        driver.quit();
    }

}
