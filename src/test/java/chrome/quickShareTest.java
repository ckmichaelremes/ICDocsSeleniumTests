package chrome;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class quickShareTest extends TestCase
{
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/michaelremes/documents/driver/chrome/chromedriver");
        driver = new ChromeDriver();
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



    public void testQuickShare(){






    }



    @After
    public void exitDriver(){
        driver.quit();
    }

}