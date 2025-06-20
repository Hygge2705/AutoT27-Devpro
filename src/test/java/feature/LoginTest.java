package feature;

import action.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import untils.Hook;

public class LoginTest extends Hook {
    LoginPage loginPage;

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginSuccessfully(){
        // Kiểm tra tiêu đề và URL
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "URL mismatch!");

        // Điền thông tin đúng
//        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        driver.findElement(By.id("login-button")).click();
        loginPage.inputLogin("standard_user", "secret_sauce");
        loginPage.clickLoginButton();

        // Xác nhận vào trang inventory
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Login failed!");

        // Kiểm tra logo & sản phẩm
//        WebElement appLogo = driver.findElement(By.className("app_logo"));
//        Assert.assertTrue(appLogo.isDisplayed(), "Logo not displayed!");
        loginPage.isLogoDisplayed();

//        int itemCount = driver.findElements(By.className("inventory_item")).size();
//        Assert.assertTrue(itemCount > 0, "No products displayed!");
        loginPage.getInventoryItemCount();
    }

    @Test
    public void testLoginWithEmptyUsername(){
        // Kiểm tra tiêu đề và URL
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "URL mismatch!");

        // Điền thông tin: bỏ trống username
        loginPage.inputLogin("", "secret_sauce");
        loginPage.clickLoginButton();
//        driver.findElement(By.id("user-name")).sendKeys("");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        driver.findElement(By.id("login-button")).click();

        // Xác nhận vào trang inventory
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Should stay on login page!");
//        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match") || loginPage.getErrorMessage().contains("Epic sadface"), "Unexpected error message!");
    }

    @Test
    public void testLoginWithEmptyPassword(){
        // Kiểm tra tiêu đề và URL
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "URL mismatch!");

        // Điền thông tin: bỏ trống password
        loginPage.inputLogin("standard_user", "");
        loginPage.clickLoginButton();
//        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        driver.findElement(By.id("password")).sendKeys("");
//        driver.findElement(By.id("login-button")).click();

        // Xác nhận vào trang inventory
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Should stay on login page!");
        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed!");
        Assert.assertTrue(errorMsg.getText().contains("Username and password do not match") || errorMsg.getText().contains("Epic sadface"), "Unexpected error message!");
    }

    @Test
    public void testLoginWithInvalidUsername(){
        // Kiểm tra tiêu đề và URL
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "URL mismatch!");

        // Điền thông tin đúng
        driver.findElement(By.id("user-name")).sendKeys("standard_user1");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Xác nhận vào trang inventory
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Should stay on login page!");
        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed!");
        Assert.assertTrue(errorMsg.getText().contains("Username and password do not match") || errorMsg.getText().contains("Epic sadface"), "Unexpected error message!");
    }

    @Test
    public void testLoginWithInvalidPassword(){
        driver.get("https://www.saucedemo.com/");

        // Kiểm tra tiêu đề và URL
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "URL mismatch!");

        // Điền thông tin đúng
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret");
        driver.findElement(By.id("login-button")).click();

        // Xác nhận vào trang inventory
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Should stay on login page!");
        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed!");
        Assert.assertTrue(errorMsg.getText().contains("Username and password do not match") || errorMsg.getText().contains("Epic sadface"), "Unexpected error message!");
    }

}
