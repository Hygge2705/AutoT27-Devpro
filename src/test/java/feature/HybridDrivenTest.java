package feature;

import action.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import untils.ExcelUntils;
import untils.Hook;

import java.util.List;
import java.util.Map;

public class HybridDrivenTest extends Hook {
    LoginPage loginPage;

    String excelFilePath = "dataLogin.xlsx";

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage(driver);
    }

    public void inputData(String user, String pass){
        loginPage.inputLogin(user,pass);
    }

    public void clickToLogin(){
        loginPage.clickLoginButton();
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        List<Map<String, String>> excelData = ExcelUntils.readExcelData(excelFilePath, "UserData");
        Object[][] data = new Object[excelData.size()][1]; // Mỗi row là 1 Map<String, String>
        for (int i = 0; i < excelData.size(); i++) {
            data[i][0] = excelData.get(i);
        }
        return data;
    }

    @Test(dataProvider = "loginData")
    public void testLogin(Map<String, String> rowData) {
        String username = rowData.get("Username");
        String password = rowData.get("Password");
        String expectedResult = rowData.get("ExpectedResult");

        inputData(username,password);
        clickToLogin();

        if ("pass".equalsIgnoreCase(expectedResult)) {
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Login failed unexpectedly!");
        } else if ("fail".equalsIgnoreCase(expectedResult)) {
            WebElement error = driver.findElement(By.xpath("//h3[@data-test='error']"));
            Assert.assertTrue(error.isDisplayed(), "Expected error message not displayed!");
        } else {
            Assert.fail("ExpectedResult trong Excel phải là 'pass' hoặc 'fail'");
        }
    }
}