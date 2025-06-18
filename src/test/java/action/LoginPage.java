package action;

import org.openqa.selenium.WebDriver;
import ui.LoginPageUI;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void inputLogin(String username, String password){
        driver.findElement(LoginPageUI.USERNAME_FIELD).sendKeys(username);
        driver.findElement(LoginPageUI.PASSWORD_FIELD).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(LoginPageUI.LOGIN_BUTTON).click();
    }

    public String getErrorMessage(){
        return driver.findElement(LoginPageUI.ERROR_MESSAGE).getText();
    }

    public boolean isLogoDisplayed() {
        return driver.findElement(LoginPageUI.APP_LOGO).isDisplayed();
    }

    public int getInventoryItemCount() {
        return driver.findElements(LoginPageUI.INVENTORY_ITEM).size();
    }
}
