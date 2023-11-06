/*
        Test Steps:

        1. Go to http://live.techpanda.org/

        2. Click on my account link

        3. Login in application using previously created credential

        4. Click on 'REORDER' link , change QTY & click Update

        5. Verify Grand Total is changed

        6. Complete Billing & Shipping Information

        7. Verify order is generated and note the order number
 */
package BAITAP;

import POM.CartPage;
import POM.CheckOutPage;
import POM.LoginPage;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;

public class TC_08 {
    @Test
    public void testTC08(){
        String email = "nhantran@gmail.com";
        String password = "nhan1234";
        String country = "United States";
        String region = "Alabama";
        String zip = "100000";
        String address = "Alabama";
        String city = "Alabama";
        String telephone = "1234566";
        int scc = 0;
        WebDriver driver = driverFactory.getChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        try {
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            //2. Click on my account link
            loginPage.clickMyAccountLink();
            //3. Login in application using previously created credential
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            Thread.sleep(2000);
            loginPage.clickLoginButton();
            Thread.sleep(2000);
            //4. Click on 'REORDER' link , change QTY & click Update
            driver.findElement(By.xpath("(//a[@class='link-reorder'][normalize-space()='Reorder'])[1]")).click();
            Thread.sleep(2000);
            CartPage cartPage = new CartPage(driver);
            //5. Verify Grand Total is changed
            cartPage.enterCountry(country);
            cartPage.enterRegion(region,country);
            cartPage.enterZip(zip);
            Thread.sleep(2000);
            cartPage.clickEstimateButton();
            Thread.sleep(2000);
            cartPage.clickFlatRateButton();
            cartPage.clickUpdateTotalButton();
            double grandTotal = Double.parseDouble(driver.findElement(By.xpath("(//td[@class='a-right'])[2]")).getText().substring(1));
            driver.findElement(By.xpath("(//input[@title='Qty'])[1]")).clear();
            driver.findElement(By.xpath("(//input[@title='Qty'])[1]")).sendKeys("10");
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//button[@title='Update'])[1]")).click();
            cartPage.verifyGrandTotal(grandTotal);
            //6. Complete Billing & Shipping Information
            CheckOutPage checkOutPage = new CheckOutPage(driver);
            checkOutPage.clickCheckOutButton();
            try{
                Select sAddr = new Select(driver.findElement(By.name("billing_address_id")));
                int sAddrSize = sAddr.getOptions().size();
                sAddr.selectByIndex(sAddrSize - 1);
            }catch(Exception e){
                System.out.println("No dropdown element present");
            }
            checkOutPage.enterAddress(address);
            checkOutPage.enterCity(city);
            checkOutPage.enterCountry(country);
            checkOutPage.enterRegion(region,country);
            checkOutPage.enterZip(zip);
            checkOutPage.enterTelephone(telephone);
            Thread.sleep(2000);
            checkOutPage.clickDifferentAddressButton();
            checkOutPage.clickContinueBillInfoButton();
            Thread.sleep(4000);
            try{
                Select sAddr = new Select(driver.findElement(By.name("shipping_address_id")));
                int sAddrSize = sAddr.getOptions().size();
                sAddr.selectByIndex(sAddrSize - 1);
            }catch(Exception e){
                System.out.println("No dropdown element present");
            }
            Thread.sleep(2000);
            checkOutPage.clickContinueShipInfoButton();
            Thread.sleep(4000);
            checkOutPage.clickContinueShipButton();
            Thread.sleep(6000);
            checkOutPage.clickMoneyOrderButton();
            checkOutPage.clickContinuePaymentButton();
            Thread.sleep(4000);
            checkOutPage.clickPlaceOrderButton();
            Thread.sleep(4000);
            //7. Verify order is generated and note the order number
            String orderVerifyStr = driver.findElement(By.xpath("//div[@class='main-container col1-layout']//p[1]")).getText();
            System.out.println(orderVerifyStr);
            scc = (scc+1);
            TakesScreenshot screenshot =((TakesScreenshot)driver);
            File srcFile= screenshot.getScreenshotAs(OutputType.FILE);
            String png =("D:\\IdeaProjects\\selenium-webdriver-java-master-nhantdse161743\\src\\test\\resources\\png/TC08_" + scc +".png");
            FileHandler.copy(srcFile, new File(png));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
