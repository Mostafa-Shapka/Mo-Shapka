import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RestPasswordPage {

    @FindBy(xpath = "//*[@class=\"uiHeaderTitle\"]")
    public WebElement RestPassword;


    public RestPasswordPage(WebDriver driver) {

        PageFactory.initElements(driver, this);

    }






}
