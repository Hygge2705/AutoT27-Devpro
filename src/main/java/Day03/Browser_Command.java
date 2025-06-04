package Day03;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser_Command {
    static WebDriver driver = null;
    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/");
        //Mở trực tiếp trên 1 tab
        driver.navigate().to("https://www.youtube.com/");
        //back lại trang trước đó
//        driver.navigate().back();
        //forword lại trang hiện tại
//        driver.navigate().forward();
        //refresh lại trang hiện tại
//        driver.navigate().refresh();
        driver.close();
    }
}
