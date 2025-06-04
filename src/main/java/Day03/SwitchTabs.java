package Day03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class SwitchTabs {
    static WebDriver driver = null;
    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //Mở tab đầu tiên và in ra title
        driver.get("https://saucelabs.com/request-demo");
        System.out.println("Title: " +driver.getTitle());

        // Lưu ID của tab gốc
        String originalTab = driver.getWindowHandle();

        // Mở tab mới và truy cập 1 đường link khác
        ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("window.open('https://www.youtube.com/', '_blank');");

        // Lưu ID của tất cả các tab
        Set<String> allTabs = driver.getWindowHandles();

        //Chuyển sang tab mới
        for (String tab : allTabs){
            if (!tab.equals(originalTab)){
                driver.switchTo().window(tab);
                break;
            }
        }

        //Thao tác trên tab mới
        System.out.println("Title tab02: " +driver.getTitle());
        WebElement searchBox = driver.findElement(By.name("search_query"));
        searchBox.sendKeys("conan");
        searchBox.submit();
        Thread.sleep(30000);


    }
}
