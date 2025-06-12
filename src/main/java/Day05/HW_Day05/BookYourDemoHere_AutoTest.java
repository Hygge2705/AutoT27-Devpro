package Day05.HW_Day05;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import untils.ExcelUntils;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class BookYourDemoHere_AutoTest {
    static WebDriver driver = null;

    public static void main(String[] args) {
        String filePath = "book.xlsx";
        String sheetName = "Sheet1";
        List<Map<String, String>> excelData = ExcelUntils.readExcelData(filePath, sheetName);

        driver = new EdgeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try{
            for (Map<String, String> rowData : excelData) {
                System.out.println("Dữ liệu hàng là: " + rowData);
                String dataEmail = rowData.get("Business Email");
                String dataFName = rowData.get("First Name");
                String dataLName = rowData.get("Last Name");
                String dataCompany = rowData.get("Company");
                String dataPhone = rowData.get("Phone Number");
                String dataCountry = rowData.get("Country");
                String dataInterest = rowData.get("Interest");
                String dataComments = rowData.get("Comments");

                driver.get("https://saucelabs.com/request-demo");
                System.out.println("Title: " + driver.getTitle());

                // Nhập email và chờ các trường khác xuất hiện
                WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
                inputEmail.sendKeys(dataEmail);

                // Chờ First Name hiển thị
                WebElement inputFirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName")));
                inputFirstName.sendKeys(dataFName);

                WebElement inputLastName = driver.findElement(By.id("LastName"));
                inputLastName.sendKeys(dataLName);

                WebElement inputCompany = driver.findElement(By.id("Company"));
                inputCompany.sendKeys(dataCompany);

                WebElement inputPhone = driver.findElement(By.id("Phone"));
                inputPhone.sendKeys(dataPhone);

                WebElement inputCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Country")));
                Select optionCountry = new Select(inputCountry);
                boolean foundCountry = false;
                for (WebElement option : optionCountry.getOptions()) {
                    if (option.getText().trim().equalsIgnoreCase(dataCountry.trim())) {
                        optionCountry.selectByVisibleText(option.getText());
                        foundCountry = true;
                        break;
                    }
                }
                if (!foundCountry) {
                    System.out.println("Không tìm thấy tùy chọn: " + dataCountry + " trong dropdown Country");
                    System.out.println("Form chưa được gửi");
                    continue;
                }

                WebElement inputInterest = driver.findElement(By.id("Solution_Interest__c"));
                Select optionInterest = new Select(inputInterest);
                boolean foundInterest = false;
                for (WebElement option : optionInterest.getOptions()) {
                    if (option.getText().trim().equalsIgnoreCase(dataInterest.trim())) {
                        optionInterest.selectByVisibleText(option.getText());
                        foundInterest = true;
                        break;
                    }
                }
                if (!foundInterest) {
                    System.out.println("Không tìm thấy tùy chọn: " + dataInterest + " trong dropdown Interest");
                    System.out.println("Form chưa được gửi");
                    continue;
                }


                WebElement inputComments = driver.findElement(By.id("Sales_Contact_Comments__c"));
                inputComments.sendKeys(dataComments);

                // Đồng ý điều khoản
                WebElement termsCheckbox = driver.findElement(By.id("LblmktoCheckbox_46340_0"));
                termsCheckbox.click();

                // Click nút Let's Talk
                WebElement submit = driver.findElement(By.className("mktoButton"));
                submit.click();
                Thread.sleep(10000);

                if(driver.getCurrentUrl().contains("thank-you")) {
                    System.out.println("Form gửi thành công");
                } else {
                    System.out.println("Form có thể chưa được gửi");
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            driver.quit();
        }
    }
}
