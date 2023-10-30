package BaiTap;


import driver.driverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/*

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)

4. Click on �COMPARE� button. A popup window opens

5. Verify the pop-up window and check that the products are reflected in it

Heading "COMPARE PRODUCTS" with selected products in it.

6. Close the Popup Windows

*/

public class TC04Test {
    @Test
    public static void testTC04(){
        int scc = 0;
        StringBuffer verificationErrors = new StringBuffer();
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            //2. Click on -> MOBILE -> menu
            driver.findElement(By.linkText("MOBILE")).click();
            Thread.sleep(2000);
            //3. In mobile products list , click on -> Add To Compare -> for 2 mobiles (Sony Xperia & Iphone)
            driver.findElement(By.xpath("(//a[@class='link-compare'][normalize-space()='Add to Compare'])[2]")).click();
            String comparePhone1 = driver.findElement(By.linkText("SONY XPERIA")).getText();
            driver.findElement(By.xpath("(//a[@class='link-compare'][normalize-space()='Add to Compare'])[3]")).click();
            String comparePhone2 = driver.findElement(By.linkText("IPHONE")).getText();
            System.out.println("Compare phone 1 " + comparePhone1);
            System.out.println("Compare phone 2 " + comparePhone2);
            //4. Click on -> COMPARE -> button. A popup window opens
            driver.findElement(By.xpath("(//button[@title='Compare'])[1]")).click();
            Thread.sleep(2000);
            //5. Verify the pop-up window and check that the products are reflected in it Heading "COMPARE PRODUCTS" with selected products in it.
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            String demoSite = driver.findElement(By.cssSelector("h1")).getText();
            System.out.println(demoSite);
            try{
                AssertJUnit.assertEquals("COMPARE PRODUCTS",demoSite);
            } catch (Exception e) {
                verificationErrors.append(e.toString());
            }
            Thread.sleep(2000);
            String popupmobile2 = driver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText();
            String popupmobile1 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();

            System.out.println("Pop up phone 1 " + popupmobile1);
            System.out.println("Pop up phone 2 " + popupmobile2);

            AssertJUnit.assertEquals(popupmobile1, comparePhone1);
            AssertJUnit.assertEquals(popupmobile2, comparePhone2);

            Thread.sleep(2000);
            //6. Close the Popup Windows
            driver.close();
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
