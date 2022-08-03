import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class DriverSetup {
    public static WebDriver driver;

    @BeforeSuite

    public void openBrowser(){

        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");

        driver=new ChromeDriver();
        driver.manage().deleteAllCookies();

    }

    @AfterSuite
    public void closeBrowser(){

        driver.close();


    }


}
