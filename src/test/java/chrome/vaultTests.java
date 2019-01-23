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


public class vaultTests extends TestCase {

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

//    public void testLoadVaulDashboard() {
//
//        // wait for homepage
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/div/span/span")));
//
//        // check URL and title
//        assertEquals("https://dev2.icdocs.co.uk/", driver.getCurrentUrl());
//
//
//        //driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div[2]/div[2]/div[2]/div[1]/div/div[1]/a")).click();
//
//        assertNotNull(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/app-steps/listing/div[2]/div[4]/div/div/div/table/tbody/tr/td[2]")));
//
//        String boxName = driver.findElement(By.xpath("/html/body/app-root/app-steps/listing/div[2]/div[4]/div/div/div/table/tbody/tr/td[2]")).getText();
//
//        assertEquals("BoxForTesting", boxName);
//
//        driver.close();
//
//    }
    public void testCreateNewVault() throws InterruptedException {

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



        driver.close();

    }
//
//
//    public void testDeleteVault() {
//
//        // wait for homepage
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/div/span/span")));
//
//        //go to vaults
//        driver.get("https://dev2.icdocs.co.uk/vaults");
//
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//        //select created Vault
//        driver.findElement(By.xpath("/html/body/app-root/app-vaults/listing/div[2]/div[5]/div/div[3]/div[3]/table/tbody/tr/td[6]/a/i")).click();
//
//
//        //delete created Vault
//        driver.findElement(By.xpath("/html/body/app-root/app-vaults/listing/div[2]/div[5]/div/div[3]/div[3]/table/tbody/tr/td[6]/div/a[1]")).click();
//
//
//        //exit deletion
//        driver.findElement(By.xpath("/html/body/jw-modal[7]/div[1]/div/div[2]/div/button[2]")).click();
//
//
//        //again
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//        //select created Vault
//        driver.findElement(By.xpath("/html/body/app-root/app-vaults/listing/div[2]/div[5]/div/div[3]/div[3]/table/tbody/tr/td[6]/a/i")).click();
//
//
//        //delete created Vault
//        driver.findElement(By.xpath("/html/body/app-root/app-vaults/listing/div[2]/div[5]/div/div[3]/div[3]/table/tbody/tr/td[6]/div/a[1]")).click();
//
//
//        //delete
//        driver.findElement(By.xpath("/html/body/jw-modal[7]/div[1]/div/div[2]/div/button[1]")).click();
//
//        driver.close();
//
//    }



//    public void testCollaborators() {
//
//        // wait for homepage
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/div/span/span")));
//
//        // check URL and title
//        assertEquals("https://dev2.icdocs.co.uk/", driver.getCurrentUrl());
//
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//        driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div[2]/div[2]/div[2]/div[1]/div/div[1]/a")).click();
//
//        assertNotNull(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/app-steps/listing/div[2]/div[4]/div/div/div/table/tbody/tr/td[2]")));
//
//        String boxName = driver.findElement(By.xpath("/html/body/app-root/app-steps/listing/div[2]/div[4]/div/div/div/table/tbody/tr/td[2]")).getText();
//
//        assertEquals("BoxForTesting", boxName);
//
//
//        driver.findElement(By.xpath("/html/body/app-root/app-steps/listing/div[2]/div[4]/div/div/div/div/div/div/a/i")).click();
//
//
//        driver.findElement(By.xpath("/html/body/app-root/app-steps/listing/div[1]/div/a[2]")).click();
//
//
//        //correct popUp
//        assertEquals("Add Collaborators to the box 'BoxForTesting'", driver.findElement(By.xpath("/html/body/jw-modal[10]/div[1]/div/div/div/span")).getText());
//
//
//
//        WebElement collaboratorInput = driver.findElement(By.xpath("/html/body/jw-modal[10]/div[1]/div/div/div/input"));
//
//        collaboratorInput.sendKeys("michael.remes@cloudkickers.cz");
//
//        driver.close();
//
//    }


    @After
    public void exitDriver() {
        driver.quit();
    }

}