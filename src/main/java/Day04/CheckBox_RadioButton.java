package Day04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CheckBox_RadioButton {
    static WebDriver driver = null;

    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/radio.html");
        System.out.println("Title: " + driver.getTitle());

//Checkbox
        List<WebElement> checkboxes = new ArrayList<>();
        checkboxes.add(driver.findElement(By.id("vfb-6-0")));
        checkboxes.add(driver.findElement(By.id("vfb-6-1")));

        for (WebElement checkbox : checkboxes){
            checkbox.click();
            System.out.print("\nCheckbox value selected: " + checkbox.getAttribute("value"));
            System.out.print("\nIs selected? " + checkbox.isSelected());
        }

        System.out.println("Successfully!");

//radio button
        WebElement  radioButton = driver.findElement(By.id("vfb-7-1"));
        radioButton.click();
        Thread.sleep(8000);

        String value = radioButton.getAttribute("value");
        boolean isSelected = radioButton.isSelected();


        System.out.print("\nRadio Button value selected: " + value);
        System.out.print("\nIs selected? " + isSelected);

        driver.quit();
    }
}
