package safari;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class homepageTest extends TestCase
{
    private WebDriver driver;

    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
        driver = new SafariDriver();
        wait = new WebDriverWait(driver, 10);

        String authURL = "https://dev2.icdocs.co.uk/auth";
        driver.get(authURL);

        // check URL and title
        assertEquals("https://dev2.icdocs.co.uk/auth", driver.getCurrentUrl());
        assertEquals("IC-Docs | Secure Document Storage for Accountancy", driver.getTitle());

        // fill in credentials
        WebElement usernameElement = driver.findElement(By.id("mat-input-0"));
        WebElement passwordElement = driver.findElement(By.id("mat-input-1"));


        //log in
        usernameElement.sendKeys("michael.remes@cloudkickers.cz");
        passwordElement.sendKeys("testingPassForICD45$");


        // submit
        driver.findElement(By.xpath("/html/body/app-root/app-auth/div/div/div[2]/app-login/form/div[3]/button")).click();

    }


    public void testHomePageContents(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/div/span/span")));

        assertEquals("https://dev2.icdocs.co.uk/", driver.getCurrentUrl());

        assertEquals("IC-Docs | Secure Document Storage for Accountancy", driver.getTitle());

        assertEquals("MichaelRemes", driver.findElement(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/span/div[2]/a[2]")).getText());


        assertEquals("QuickShare Folders", driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div[1]/h3[2]")).getText());

        assertEquals("Quick Access", driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div[2]/div[2]/div[1]/h3")).getText());

        assertEquals("Recent Activities", driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div[2]/div[3]/h3")).getText());


        driver.close();

    }



    @After
    public void exitDriver(){
        driver.quit();
    }

}