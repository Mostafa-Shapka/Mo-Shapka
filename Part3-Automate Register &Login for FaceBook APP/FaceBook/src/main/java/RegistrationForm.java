import Helper.DataFaker;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.PublicKey;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;

public class RegistrationForm  {

    DataFaker fake =new DataFaker();
    public String REGUName= fake.RegUserName;



    @FindBy(xpath = "//*[@class=\"mbs _52lq fsl fwb fcb\"]")
    public WebElement Signup;
    @FindBy(xpath = "//*[@name=\"firstname\"]")
    public WebElement UserName;
    @FindBy(xpath = "//*[@name=\"lastname\"]")
    public WebElement SurName;
    @FindBy(xpath = "//*[@name=\"reg_email__\"]")
    public WebElement EmailorMobile;
    @FindBy(xpath = "//*[@name=\"reg_email_confirmation__\"]")
    public WebElement MailConfirmation;
    @FindBy(xpath = "//*[@id=\"password_step_input\"]")
    public WebElement Password;
    @FindBy(xpath = "//*[@id=\"day\"]")
    public  WebElement Day;
    @FindBy(xpath = "//*[@id=\"month\"]")
    public WebElement Month;
    @FindBy(xpath = "//*[@id=\"year\"]")
    public WebElement Year;
    @FindBy(xpath = "//*[@name=\"sex\" and @value=\"1\"]")
    public WebElement Female;
    @FindBy(xpath = "//*[@name=\"sex\" and @value=\"2\"]")
    public WebElement Male;
    @FindBy(xpath = "//*[@name=\"sex\" and @value=\"-1\"]")
    public WebElement Custom;
    @FindBy(xpath = "//*[@name=\"websubmit\"]")
    public WebElement SignUpBTN;

    @FindBy(xpath = "//*[@name=\"preferred_pronoun\"]")
    public WebElement preferred_pronoun;
    @FindBy(xpath = "//*[contains (@id,'js') and @class=\"_5633 _5634 _53ij\" ]")
    public WebElement WrongEmailorPhone;



    @FindBy (xpath = "//*[@class=\"cxmmr5t8 oygrvhab hcukyx3x c1et5uql o9v6fnle\"]")
    public WebElement REGMSG;
    @FindBy (xpath = "//*[@class=\"a8c37x1j ni8dbmo4 stjgntxs l9j0dhe7 ltmttdrg g0qnabr5\"]")
    public WebElement REGFName;





    public RegistrationForm (WebDriver driver) {

        PageFactory.initElements(driver, this);


    }

    public void sendData(JsonObject RegistrationData ,String RegisterType ,String Gender,String InputData ){

if (InputData=="True"){
        UserName.sendKeys(REGUName);
        SurName.sendKeys(fake.SurName);
        Password.sendKeys(fake.Password);
        if (RegisterType=="Email"){

            EmailorMobile.sendKeys(fake.Email);
            MailConfirmation.sendKeys(fake.Email);

        } else if (RegisterType=="Phone") {
            EmailorMobile.sendKeys(fake.Mobile);} else if (RegisterType=="InvalidEmail") {
            EmailorMobile.sendKeys(RegistrationData.get("InvalidEmail").getAsString());}else if (RegisterType=="InvalidPhoneNumber") {
            EmailorMobile.sendKeys(RegistrationData.get("InvalidPhoneNumber").getAsString());}
        else if (RegisterType=="MailMisMatch") {

                EmailorMobile.sendKeys(RegistrationData.get("ValidMail").getAsString());
                MailConfirmation.sendKeys(fake.Email);

            }




        Select RegDay = new Select(Day);
        RegDay.selectByValue(RegistrationData.get("day").getAsString());
        Select RegMonth = new Select(Month);
        RegMonth.selectByValue(RegistrationData.get("Month").getAsString());
        Select RegYear = new Select(Year);
        RegYear.selectByValue(RegistrationData.get("Year").getAsString());

        if (Gender== "RegWithMale"){

            Male.click();
        } else if (Gender== "RegWithFemale") {
            Female.click();
        } else{
            Custom.click();
            Select pronoun = new Select(preferred_pronoun);
            pronoun.selectByValue("1");


        }
        SignUpBTN.click();}
else if (InputData=="False") {
    SignUpBTN.click();

}










    }




    }
















