package BaiTap;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TC03Test {
    /*

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on -> MOBILE -> menu

3. In the list of all mobile , click on -> ADD TO CART -> for Sony Xperia mobile

4. Change -> QTY -> value to 1000 and click -> UPDATE -> button. Expected that an error is displayed "The requested quantity for "Sony Xperia" is not available.

5. Verify the error message

6. Then click on -> EMPTY CART -> link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.

7. Verify cart is empty
*/
        @Test
        public static void testTC03(){
            int scc = 0;
            WebDriver driver = driverFactory.getChromeDriver();
            try {
                //1. Go to http://live.techpanda.org/
                driver.get("http://live.techpanda.org/");
                //2. Click on -> MOBILE -> menu
                driver.findElement(By.linkText("MOBILE")).click();
                Thread.sleep(2000);
                //3. In the list of all mobile , click on -> ADD TO CART -> for Sony Xperia mobile
                driver.findElement(By.xpath("(//button[@title='Add to Cart'])[2]")).click();
                //4. Change -> QTY -> value to 1000 and click -> UPDATE -> button. Expected that an error is displayed "The requested quantity for "Sony Xperia" is not available.
                driver.findElement(By.xpath("(//input[@title='Qty'])[1]")).clear();
                driver.findElement(By.xpath("(//input[@title='Qty'])[1]")).sendKeys("1000");
                Thread.sleep(2000);
                driver.findElement(By.xpath("(//button[@title='Update'])[1]")).click();
                Thread.sleep(2000);
                //5. Verify the error message
                String reqQty = driver.findElement(By.xpath("(//p[@class='item-msg error'])[1]")).getText();
                String msgQty = ("* The requested quantity for \"Sony Xperia\" is not available.");
                AssertJUnit.assertEquals(msgQty, reqQty);
                Thread.sleep(2000);

                //6. Then click on -> EMPTY CART -> link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.
                driver.findElement(By.xpath("(//span[contains(text(),'Empty Cart')])[1]")).click();
                Thread.sleep(2000);
                //7. Verify cart is empty
                String noItemStg = ("You have no items in your shopping cart.");
                String noItemMsg = driver.findElement(By.xpath("(//p[contains(text(),'You have no items in your shopping cart.')])[2]")).getText();
                System.out.println("You have no item msg = " + noItemMsg);

                AssertJUnit.assertEquals(noItemMsg, noItemStg);

                Thread.sleep(2000);

            } catch (Exception e){
                e.printStackTrace();
            }
            driver.quit();
        }
    }

