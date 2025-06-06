package Day04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class Double_Click {
    static WebDriver driver = null;

    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        System.out.println("Title: " + driver.getTitle());

//    Thực hiện double click
        WebElement btnDoubleClick =driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));

        Actions action = new Actions(driver);
        action.doubleClick(btnDoubleClick).perform();

//    Xử lý cảnh báo "alert" xuất hiện sau khi bouble click:
        String alertText = driver.switchTo().alert().getText();
        System.out.print("\nAlert text after double click: '"+alertText +"'");

//    Đóng alert
        driver.switchTo().alert().accept();

        driver.quit();

    }
}
