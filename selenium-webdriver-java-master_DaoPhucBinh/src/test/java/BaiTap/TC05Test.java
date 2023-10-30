package BaiTap;

import POM.RegisterPage;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;

/*
Test Steps:

1. Go to http://live.techpanda.org/

2. Click on my account link

3. Click Create an Account link and fill New User information excluding the registered Email ID.

4. Click Register

5. Verify Registration is done. Expected account registration done.

6. Go to TV menu

7. Add product in your wish list - use product - LG LCD

8. Click SHARE WISHLIST

9. In next page enter Email and a message and click SHARE WISHLIST

10.Check wishlist is shared. Expected wishlist shared successfully.

*/

public class TC05Test {
    @Test
    public static void testTC05(){
        String firstName = "binh";
        String lastName = "dao";
        String email_address = "daobinh3@gmail.com";
        String password = "binh1234";
        String confirmPassword = password;

        //init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Click on my account link
            RegisterPage rg = new RegisterPage(driver);
            rg.clickMyAccountLink();

            //3a. Click Create an Account link
            rg.clickCreateAccountLink();

            //3b. Fill New User information excluding the registered Email ID.
            rg.enterFirstName(firstName);
            rg.enterLastName(lastName);
            rg.enterEmail(email_address);
            rg.enterPassword(password);
            rg.enterConfirmPassword(confirmPassword);

            //4. Click Register
            rg.clickRegisterButton();

            //switching to new windows
            for(String handle : driver.getWindowHandles()){
                driver.switchTo().window(handle);
            }

            //5. Verify Registration is done. Expected account registration done.
            String upperCaseFirstName = firstName.toUpperCase();
            String upperCaseLastName = lastName.toUpperCase();
            String expectedWelcome = ("WELCOME, " + upperCaseFirstName+ " "+  upperCaseLastName+ "!");
            String actualWelcome = driver.findElement(By.xpath("(//p[@class='welcome-msg'])[1]")).getText();

            AssertJUnit.assertEquals(expectedWelcome, actualWelcome);

            //6. Go to TV menu
            driver.findElement(By.linkText("TV")).click();
            Thread.sleep(2000);

            //7. Add product in your wish list - use product - LG LCD
            driver.findElement(By.xpath("(//a[@class='link-wishlist'][normalize-space()='Add to Wishlist'])[1]")).click();
            Thread.sleep(2000);

            //8. Click SHARE WISHLIST
            driver.findElement(By.xpath("(//button[@title='Share Wishlist'])[1]")).click();
            Thread.sleep(2000);

            //9. In next page enter Email and a message and click SHARE WISHLIST
            String wishListEmail = "cusbinh@gmail.com";
            String wishListMsg = "wish you like it";
            driver.findElement(By.xpath("(//textarea[@id='email_address'])[1]")).clear();
            driver.findElement(By.xpath("(//textarea[@id='email_address'])[1]")).sendKeys(wishListEmail);
            driver.findElement(By.xpath("(//textarea[@id='message'])[1]")).clear();
            driver.findElement(By.xpath("(//textarea[@id='message'])[1]")).sendKeys(wishListMsg);
            Thread.sleep(2000);

            driver.findElement(By.xpath("(//button[@title='Share Wishlist'])[1]")).click();;
            //10.Check wishlist is shared. Expected wishlist shared successfully.
            String wishListText = driver.findElement(By.xpath("(//li)[20]")).getText();
            System.out.println(wishListText);
            String expectedText = ("Your Wishlist has been shared.");
            AssertJUnit.assertEquals(expectedText,wishListText);

            int scc = 0;
            scc = (scc+1);
            TakesScreenshot screenshot =((TakesScreenshot)driver);
            File srcFile= screenshot.getScreenshotAs(OutputType.FILE);
            String png =("/Users/daophucbinh/Downloads/selenium-webdriver-java-master_DaoPhucBinh/src/test/resources/png/TC05_" + scc +".png");
            FileHandler.copy(srcFile, new File(png));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

}
