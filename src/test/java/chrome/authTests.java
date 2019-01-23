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



public class authTests extends TestCase
{
    private WebDriver driver;

    private WebElement usernameElement;
    private WebElement passwordElement;
    private String userName = "michael.remes@cloudkickers.cz";
    private String baseURL = "https://dev2.icdocs.co.uk/";
    private String authURL = "https://dev2.icdocs.co.uk/auth";
    private WebDriverWait wait;

    @Before
    public void setUp(){
       System.setProperty("webdriver.chrome.driver", "/Users/michaelremes/documents/driver/chrome/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }



    public void testSuccessfulLogIn(){

        // go to IC-Docs webpage
        driver.get(baseURL);

        // go to login page
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/a[1]")).click();

        // check URL and title
        assertEquals("https://dev2.icdocs.co.uk/auth", driver.getCurrentUrl());

        // fill in credentials
        WebElement usernameElement = driver.findElement(By.id("mat-input-0"));
        WebElement passwordElement = driver.findElement(By.id("mat-input-1"));

        usernameElement.sendKeys(userName);
        passwordElement.sendKeys("testingPassForICD45$");

        // submit
        driver.findElement(By.xpath("/html/body/app-root/app-auth/div/div/div[2]/app-login/form/div[3]/button")).click();


        // Wait for Homepage to be present and check URL
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/span/div[2]/a[1]/i")));
        assertEquals("https://dev2.icdocs.co.uk/", driver.getCurrentUrl());
        assertEquals("IC-Docs | Secure Document Storage for Accountancy", driver.getTitle());

        driver.close();

    }

    public void testWrongPassword(){

        // go to IC-Docs webpage
        driver.get(authURL);

        // check URL and title
        assertEquals("https://dev2.icdocs.co.uk/auth", driver.getCurrentUrl());
        assertEquals("IC-Docs | Secure Document Storage for Accountancy", driver.getTitle());

        // fill in credentials
        usernameElement = driver.findElement(By.id("mat-input-0"));
        passwordElement = driver.findElement(By.id("mat-input-1"));


        //wrong password
        usernameElement.sendKeys(userName);
        passwordElement.sendKeys("wrongPassword#");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // submit
        driver.findElement(By.xpath("/html/body/app-root/app-auth/div/div/div[2]/app-login/form/div[3]/button")).click();

        // check URL
        assertEquals("https://dev2.icdocs.co.uk/auth", driver.getCurrentUrl());
        //Incorrect username or password was showed
        assertEquals("Incorrect username or password.",
                driver.findElement(By.xpath("/html/body/app-root/app-auth/div/div/div[2]/app-login/form/div[2]/div")).getText());


        driver.close();

    }

    public void testNonExistingUser(){

        // go to IC-Docs webpage
        driver.get(authURL);

        // check URL and title
        assertEquals("https://dev2.icdocs.co.uk/auth", driver.getCurrentUrl());
        assertEquals("IC-Docs | Secure Document Storage for Accountancy", driver.getTitle());

        // fill in credentials
        usernameElement = driver.findElement(By.id("mat-input-0"));
        passwordElement = driver.findElement(By.id("mat-input-1"));

        //wrong username
        usernameElement.sendKeys("nonexisting.user.1892383189321@cloudkickers.cz");
        passwordElement.sendKeys("password");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // submit
        driver.findElement(By.xpath("/html/body/app-root/app-auth/div/div/div[2]/app-login/form/div[3]/button")).click();

        //User doesn't exists
        assertEquals("User does not exist.",
                driver.findElement(By.xpath("/html/body/app-root/app-auth/div/div/div[2]/app-login/form/div[2]/div")).getText());
        // check URL
        assertEquals("https://dev2.icdocs.co.uk/auth", driver.getCurrentUrl());

        driver.close();

    }

    public void testLogOut(){
        // go to IC-Docs webpage
        driver.get(baseURL);

        // go to login page
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/a[1]")).click();

        // check URL and title
        assertEquals("https://dev2.icdocs.co.uk/auth", driver.getCurrentUrl());
        //     assertEquals("IC-Docs", driver.getTitle());

        // fill in credentials
        WebElement usernameElement = driver.findElement(By.id("mat-input-0"));
        WebElement passwordElement = driver.findElement(By.id("mat-input-1"));

        usernameElement.sendKeys(userName);
        passwordElement.sendKeys("testingPassForICD45$");

        // submit
        driver.findElement(By.xpath("/html/body/app-root/app-auth/div/div/div[2]/app-login/form/div[3]/button")).click();



        // Wait for Homepage to be present and check URL
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/span/div[2]/a[1]/i")));
        assertEquals("https://dev2.icdocs.co.uk/", driver.getCurrentUrl());


        driver.findElement(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/span/div[2]/a[1]/i")).click();
        driver.findElement(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/span/div[2]/div[2]/div/div/a")).click();
        assertEquals("Are you sure you want to log out?", driver.findElement(By.xpath("/html/body/jw-modal[4]/div[1]/div/div[1]/h2")).getText());

        // no
        driver.findElement(By.xpath("/html/body/jw-modal[4]/div[1]/div/div[2]/button[2]/span")).click();


        //exit
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/span/div[2]/a[1]/i")));
        driver.findElement(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/span/div[2]/a[1]/i")).click();
        driver.findElement(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/span/div[2]/div[2]/div/div/a")).click();
        assertEquals("Are you sure you want to log out?", driver.findElement(By.xpath("/html/body/jw-modal[4]/div[1]/div/div[1]/h2")).getText());
        driver.findElement(By.xpath("/html/body/jw-modal[4]/div[1]/div/a/i")).click();


        // log out
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/span/div[2]/a[1]/i")));
        driver.findElement(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/span/div[2]/a[1]/i")).click();
        driver.findElement(By.xpath("/html/body/app-root/app-dashboard/app-top-menu/div/div/span/div[2]/div[2]/div/div/a")).click();
        assertEquals("Are you sure you want to log out?", driver.findElement(By.xpath("/html/body/jw-modal[4]/div[1]/div/div[1]/h2")).getText());
        driver.findElement(By.xpath("/html/body/jw-modal[4]/div[1]/div/div[2]/button[1]/span")).click();

        driver.close();
    }


    @After
    public void exitDriver(){
       driver.quit();
    }

}