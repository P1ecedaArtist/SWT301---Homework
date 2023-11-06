package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage {
    WebDriver driver;
    By checkOutButton = By.xpath("(//button[@title='Proceed to Checkout'])[2]");
    By addressInputLocator = By.id("billing:street1");
    By cityInputLocator = By.id("billing:city");
    By provinceInputLocator = By.id("billing:region");
    By stateInputLocator = By.id("billing:region_id");
    By zipInputLocator = By.id("billing:postcode");
    By countryInputLocator = By.id("billing:country_id");
    By telephoneInputLocator = By.id("billing:telephone");
    By differentAddressButton = By.xpath("(//input[@id='billing:use_for_shipping_no'])[1]");
    By continueBillInfoButton = By.xpath("(//button[@onclick='billing.save()'])[1]");
    By continueShipInfoButton = By.xpath("(//button[@onclick='shipping.save()'])[1]");
    By continueShippingButton = By.xpath("(//button[@onclick='shippingMethod.save()'])[1]");
    By moneyOrderButton = By.xpath("(//input[@id='p_method_checkmo'])[1]");
    By continuePaymentButton = By.xpath("(//button[@onclick='payment.save()'])[1]");
    By placeOrderButton = By.xpath("(//button[@title='Place Order'])[1]");

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickCheckOutButton(){
        driver.findElement(checkOutButton).click();
    }
    public void enterAddress(String address){
        WebElement addressElement = driver.findElement(addressInputLocator);
        addressElement.clear();
        addressElement.sendKeys(address);
    }
    public void enterCity(String city){
        WebElement cityElement = driver.findElement(cityInputLocator);
        cityElement.clear();
        cityElement.sendKeys(city);
    }
    public void enterCountry(String country){
        WebElement countryElement = driver.findElement(countryInputLocator);
        new Select(countryElement).selectByVisibleText(country);
    }
    public void enterRegion(String region, String country){
        if(country.equals("United States")){
            WebElement stateElement = driver.findElement(stateInputLocator);
            new Select (stateElement).selectByVisibleText(region);
        } else {
            WebElement provinceElement = driver.findElement(provinceInputLocator);
            provinceElement.clear();
            provinceElement.sendKeys(region);
        }
    }
    public void enterZip(String zip){
        WebElement zipElement = driver.findElement(zipInputLocator);
        zipElement.clear();
        zipElement.sendKeys(zip);
    }
    public void enterTelephone(String telephone){
        WebElement telephoneElement = driver.findElement(telephoneInputLocator);
        telephoneElement.clear();
        telephoneElement.sendKeys(telephone);
    }
    public void clickDifferentAddressButton() {driver.findElement(differentAddressButton).click();}
    public void clickContinueBillInfoButton(){
        driver.findElement(continueBillInfoButton).click();
    }
    public void clickContinueShipInfoButton(){
        driver.findElement(continueShipInfoButton).click();
    }
    public void clickContinueShipButton(){
        driver.findElement(continueShippingButton).click();
    }
    public void clickMoneyOrderButton(){
        driver.findElement(moneyOrderButton).click();
    }
    public void clickContinuePaymentButton(){
        driver.findElement(continuePaymentButton).click();
    }
    public void clickPlaceOrderButton(){
        driver.findElement(placeOrderButton).click();
    }
}
