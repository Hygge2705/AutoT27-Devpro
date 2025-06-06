package Day04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DropDownSelect {
    static WebDriver driver = null;

    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        System.out.println("Title: " + driver.getTitle());

        WebElement inputInterest = driver.findElement(By.id("Solution_Interest__c"));
        Select optionInterest = new Select(inputInterest);
        optionInterest.selectByValue("Mobile Application Testing");
        System.out.println("Interest select by value successfully!");


        optionInterest.selectByIndex(1);
        System.out.println("Interest select by Index successfully!");

        optionInterest.selectByVisibleText("Mobile Application Testing");
        System.out.println("Interest select by VisibleText successfully!");

        driver.quit();
    }

}
