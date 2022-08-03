import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    @FindBy  (xpath = "//*[@id=\"email\"]")
    public WebElement EmailORPhoneNumber;
    @FindBy  (xpath = "//*[@id=\"pass\"]")
    public WebElement Password;
    @FindBy (xpath = "//*[@name=\"login\"]")
    public WebElement LoginBTN;
    @FindBy(xpath = "//*[@class=\"_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy\"]")
    public WebElement CreateAccountBTN;
    @FindBy(xpath = "//*[@class=\"_6ltj\"]")
    public WebElement ForgetPassword;
    @FindBy(xpath = "//*[@class=\"_9ay7\"]")
    public WebElement InvalidLogInMSG;
    public LoginPage(WebDriver driver) {

        PageFactory.initElements(driver, this);

    }


public void sendLoginData(JsonObject LoginData , String LoginCase){

        if (LoginCase=="ValidLogin"){

        EmailORPhoneNumber.sendKeys(LoginData.get("Email").getAsString());
        Password.sendKeys(LoginData.get("Password").getAsString());
        LoginBTN.click();}

        else if (LoginCase=="InvalidLogin"){
            EmailORPhoneNumber.sendKeys(LoginData.get("Email").getAsString());
            Password.sendKeys(LoginData.get("InvalidPassword").getAsString());
            LoginBTN.click();}

            else if (LoginCase=="ForgetPassword"){
            EmailORPhoneNumber.sendKeys(LoginData.get("Email").getAsString());
            ForgetPassword.click();


    } else if (LoginCase=="NoInput") {
            LoginBTN.click();

        }


}
public void createNewAccount(){

        CreateAccountBTN.click();
    }

    public void ForgetPasswordLink() {

        ForgetPassword.click();
    }






}
