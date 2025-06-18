package feature;

import action.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import untils.Hook;

public class LoginTestModuleTest extends Hook {
    LoginPage loginPage;

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

    @Test
    public void loginPageWithValidData(){
        inputData("standard_user", "secret_sauce");
        clickToLogin();
    }

    @Test
    public void testLoginSuccessfully(){
        // Kiểm tra tiêu đề và URL
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "URL mismatch!");

        // Điền thông tin đúng
        inputData("standard_user", "secret_sauce");
        clickToLogin();

        // Xác nhận vào trang inventory
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Login failed!");

        // Kiểm tra logo & sản phẩm
        loginPage.isLogoDisplayed();
        loginPage.getInventoryItemCount();
    }

    @Test
    public void testLoginWithEmptyUsername(){
        // Kiểm tra tiêu đề và URL
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "URL mismatch!");

        // Điền thông tin: bỏ trống username
        inputData("", "secret_sauce");
        clickToLogin();
        // Xác nhận báo lỗi
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Should stay on login page!");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match") || loginPage.getErrorMessage().contains("Epic sadface"), "Unexpected error message!");
    }

    @Test
    public void testLoginWithEmptyPassword(){
        // Kiểm tra tiêu đề và URL
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "URL mismatch!");

        // Điền thông tin: bỏ trống password
        inputData("standard_user", "");
        clickToLogin();

        // Xác nhận báo lỗi
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Should stay on login page!");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match") || loginPage.getErrorMessage().contains("Epic sadface"), "Unexpected error message!");
    }
}
