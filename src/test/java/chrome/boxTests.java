package chrome;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class boxTests extends TestCase {

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

        // fill in credentials
        WebElement usernameElement = driver.findElement(By.id("mat-input-0"));
        WebElement passwordElement = driver.findElement(By.id("mat-input-1"));

        // log in
        usernameElement.sendKeys("michael.remes@cloudkickers.cz");
        passwordElement.sendKeys("testingPassForICD45$");

        // submit
        driver.findElement(By.xpath("/html/body/app-root/app-auth/div/div/div[2]/app-login/form/div[3]/button")).click();

    }


    public void testCreateBox() throws InterruptedException {

        // wait for homepage
        wait.until(ExpectedConditions.urlMatches("https://dev2.icdocs.co.uk"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/div/span/span")));


        //go to vaults page
        driver.get("https://dev2.icdocs.co.uk/vaults");
        // wait for vault page
        wait.until(ExpectedConditions.urlMatches("https://dev2.icdocs.co.uk/vaults"));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/app-vaults/listing/div[1]/div/a")));

        // create new vault
        // ---------------

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Thread.sleep(2000);

        WebElement createNewVaultButton = driver.findElement(By.xpath("/html/body/app-root/app-vaults/listing/div[1]/div/a"));

        createNewVaultButton.click();

        driver.findElement(By.name("pendingItemName")).sendKeys("SeleniumTestingVault");

        //submit
        driver.findElement(By.xpath("/html/body/jw-modal[8]/div[1]/div/div[2]/form/button/span")).click();
        //---------------


        // create new box
        //---------------
        //driver.findElement(By.xpath("/html/body/app-root/app-vaults/listing/div[2]/div[5]/div/div[2]/div[2]/table/tbody/tr/td[2]/span/a")).click();
//        driver.findElement(By.name("SeleniumTestingVault")).click();
//
//        driver.findElement(By.xpath("/html/body/app-root/app-steps/listing/div[1]/div/a")).click();
//
//        assertEquals("Create New Box", driver.findElement(By.xpath("/html/body/jw-modal[9]/div[1]/div/div[1]/h2/span")).getText());
//
//        driver.findElement(By.name("pendingItemName")).sendKeys("SeleniumTestingBox");
//
//        driver.findElement(By.xpath("/html/body/jw-modal[9]/div[1]/div/div[2]/form/button")).click();

        //---------------




        driver.close();

    }



//    public void testLoadBoxFromHomePage() {
//
//        // wait for homepage
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/div/span/a")));
//
//        // check URL and title
//        assertEquals("https://dev2.icdocs.co.uk/", driver.getCurrentUrl());
//
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//        driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div[2]/div[2]/div[2]/div[1]/div/div[1]/div[1]/a")).click();
//
//
//
//
//        driver.close();
//
//    }


    @After
    public void exitDriver() {
        driver.quit();
    }

}