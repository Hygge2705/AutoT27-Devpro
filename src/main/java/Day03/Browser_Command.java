package Day03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser_Command {
    static WebDriver driver = null;
    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        //Mở trực tiếp trên 1 tab
//        driver.navigate().to("https://saucelabs.com/request-demo");
        //back lại trang trước đó
//        driver.navigate().back();
        //forword lại trang hiện tại
//        driver.navigate().forward();
        //refresh lại trang hiện tại
//        driver.navigate().refresh();

//        String url = driver.getCurrentUrl();
//        System.out.println("String url: " +url);

//        WebElement elementXPATH = driver.findElement(By.xpath("//input[@id=Email]"));
        WebElement elementID = driver.findElement(By.id("Email"));
//        WebElement elementNAME = driver.findElement(By.name("Email"));
//        WebElement elementTAG = driver.findElement(By.tagName("input"));
//        WebElement elementCSS = driver.findElement(By.cssSelector("#Email"));
        elementID.sendKeys("thom@gmail.com");
        elementID.clear();
        Thread.sleep(5000);
//        elementID.isEnabled();


//        WebElement elementLinkText = driver.findElement(By.linkText("Try it free"));
//        WebElement elementPartialLinkText = driver.findElement(By.partialLinkText("Try it"));
//        elementPartialLinkText.click();
//        driver.quit();
    }
}
