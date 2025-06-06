package Day04;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptExecutor {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

// Mở trang web
        driver.get("https://saucelabs.com/request-demo");

//Lấy phần tử dropList Interest bằng cách dùng id
        WebElement dropListInterest = driver.findElement(By.id("Solution_Interest__c"));

//Tạo đối tượng JavascriptExecutor, chuyển đổi driver thành JavascriptExecutor để thực thi đoạn mã Javascript
        JavascriptExecutor js = (JavascriptExecutor) driver;

// Thực thi đoạn mã và thay đổi giá trị của dropList
// Thực hiện thay đổi giá trị (giả sử trong list đó có option giá trị là "Visual Testing")
        js.executeScript("arguments[0].value='Visual Testing';",dropListInterest);

        Thread.sleep(3000);

// Kiểm tra giá trị đã được chọn
        String selectedValue = dropListInterest.getAttribute("value");
        System.out.println("Interest selected: " +selectedValue);

        driver.quit();
    }
}
