package BaiTap;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/*

Test Steps:

1. Goto http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)

4. Click on Sony Xperia mobile

5. Read the Sony Xperia mobile from detail page.

6. Compare Product value in list and details page should be equal ($100).

*/

public class TC02Test {
    @Test
    public static void testTC02(){
        StringBuffer verificationErrors = new StringBuffer();
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            //Step 2. Click on -> MOBILE -> menu
            driver.findElement(By.linkText("MOBILE")).click();
            Thread.sleep(2000);
            //3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
            String demoSite1 = driver.findElement(By.xpath("(//span[contains(text(),'$100.00')])[1]")).getText();
            System.out.println(demoSite1);
            //4. Click on Sony Xperia mobile
            driver.findElement(By.linkText("SONY XPERIA")).click();
            Thread.sleep(2000);
            //5. Read the Sony Xperia mobile from detail page.
            String demoSite2 = driver.findElement(By.xpath("(//span[@class='price'])[1]")).getText();
            System.out.println(demoSite2);
            //6. Compare Product value in list and details page should be equal ($100).
            try{
                AssertJUnit.assertEquals(demoSite1,demoSite2);
            } catch (Exception e) {
                verificationErrors.append(e.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}

