package BaiTap;

import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
/*

Test Steps

Step 1. Go to http://live.techpanda.org/

Step 2. Verify Title of the page

Step 3. Click on -> MOBILE -> menu

Step 4. In the list of all mobile , select SORT BY -> dropdown as name

Step 5. Verify all products are sorted by name

*/

@Test
public class TC01Test {
    public static void testTC01(){
        int scc = 0;

        StringBuffer verificationErrors = new StringBuffer();
        //Init web - driver session
        WebDriver driver = driverFactory.getChromeDriver();

        try{
            //Step 1: Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            //Step 2: Verify Title of the page
            String demoSite = driver.findElement(By.cssSelector("h2")).getText();
            System.out.println(demoSite);
            try{
                AssertJUnit.assertEquals("THIS IS DEMO SITE FOR   ", demoSite);
            }catch(Exception e){
                verificationErrors.append(e.toString());
            }

            //debug
            Thread.sleep(2000);

            //Step 3: Click on Mobile -> Menu
            driver.findElement(By.linkText("MOBILE")).click();

            //debug
            Thread.sleep(2000);

            //Step 4: In the list of all mobile , select SORT BY -> dropdown as name
            new Select(driver.findElement(By.xpath("(//select[@title='Sort By'])[1]"))).selectByVisibleText("Name");

            //debug
            Thread.sleep(2000);

            //step 5: Verify all products are sorted by name
            //This wil take screenshot of manager the manager's page after a successful after a successful login
            scc = (scc+1);
            TakesScreenshot screenshot =((TakesScreenshot)driver);
            File srcFile= screenshot.getScreenshotAs(OutputType.FILE);
            String png =("/Users/daophucbinh/Downloads/selenium-webdriver-java-master_DaoPhucBinh/src/test/resources/png/TC01_");
            FileHandler.copy(srcFile, new File(png));

        }catch(Exception e){
            e.printStackTrace();
        }
        //step 6: Quit browser session
        driver.quit();
    }
}
