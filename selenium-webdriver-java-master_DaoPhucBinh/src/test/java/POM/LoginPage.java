package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    //Define locators at the top
    By myAccountLink = By.linkText("MY ACCOUNT");
    By emailInputLocator = By.id("email");
    By passwordInputLocator = By.id("pass");

    By loginButton = By.id("send2");

    //Constructor with required parameter as a Driver instance
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMyAccountLink(){
        driver.findElement(myAccountLink).click();
    }


    public void enterEmail(String email){
        WebElement emailElement = driver.findElement(emailInputLocator);
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password){
        WebElement passwordElement = driver.findElement(passwordInputLocator);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }


    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }
}
