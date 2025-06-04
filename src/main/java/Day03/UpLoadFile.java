package Day03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UpLoadFile {
    static WebDriver driver = null;
    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //Mở tab đầu tiên và in ra title
        driver.get("https://demo.guru99.com/test/upload/");
        System.out.println("Title: " + driver.getTitle());

        //Tạo biến chứa đường dẫn tệp tải lên
        String filePath = "D:/HAU/Automation Test - DEVPRO-Auto27/AutoT27-Devpro/AutoT27-Devpro/target/imgMeme.jpg";

        //Tìm phần tử uploadfile_0 và gửi đường dẫn tệp
        WebElement upLoadFile = driver.findElement(By.id("uploadfile_0"));
        upLoadFile.sendKeys(filePath);

        //Đồng ý với các điều khoản
        WebElement termsCheckbox = driver.findElement(By.id("terms"));
        termsCheckbox.click();

        //Nhấp vào nút upload File
        WebElement uploadButton = driver.findElement(By.id("submitbutton"));
        uploadButton.click();

    }
}
