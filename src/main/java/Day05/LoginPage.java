package Day05;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import untils.ExcelUntils;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class LoginPage {
    static WebDriver driver = null;
    public static void main(String[] args) {
        //Tạo biến chứa đường dẫn file Excel
        String filePath = "dataLogin.xlsx";
        String sheetName = "UserData";

        //Đọc dữ liệu từ file Excel
        List<Map<String, String>> excelData = ExcelUntils.readExcelData(filePath, sheetName);

        driver = new EdgeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try{
            for (Map<String, String> rowData : excelData) {
                // Login web mua hàng sau: https://www.saucedemo.com/
                // nhap username voi du lieu hang la:
                System.out.println("Dữ liệu hàng là: " + rowData);
                String user = rowData.get("Username"); // Lấy giá trị cột username
                String pass = rowData.get("Password"); // Lấy giá trị cột password
                driver.get("https://www.saucedemo.com/");
                WebElement userName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
                userName.sendKeys(user);
                WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
                password.sendKeys(excelData.get(0).get("Password"));
                WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
                login.click();
                if (driver.getCurrentUrl().contains("inventory")){
                    System.out.println("Dang nhap thanh cong");
                }else {
                    System.out.println("Dang nhap khong thanh cong");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            driver.quit();
        }
    }
}
