
import Helper.DataFaker;
import Helper.FileReader;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

public class RegTestCases extends DriverSetup {

    RegistrationForm registrationform;
    LoginPage loginPage;
    JsonObject RegistrationData;


    @BeforeClass
    public void setupData() throws FileNotFoundException {
        FileReader ReadData =new FileReader();
        RegistrationData=ReadData.readDate("src/main/resources/RegistrationData.json");
    }

    @BeforeMethod
    public  void faceBookURL(){
        driver.manage().deleteAllCookies();
        driver.get("https://www.facebook.com/");

    }

    @BeforeClass
    public void initializations () {

        registrationform=new RegistrationForm(driver);
        loginPage=new LoginPage(driver);

    }

    @BeforeMethod
    public void openRegPage(){
        loginPage.createNewAccount();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

    }

    @Test (priority = 1)
    public void checkRegistrationFormUI (){
        Assert.assertEquals(registrationform.Signup.getText(),"Sign Up");
        Assert.assertEquals(registrationform.UserName.getAttribute("name"),"firstname");
        Assert.assertEquals(registrationform.SurName.getAttribute("name"),"lastname");
        Assert.assertEquals(registrationform.EmailorMobile.getAttribute("name"),"reg_email__");
        Assert.assertEquals(registrationform.Password.getAttribute("name"),"reg_passwd__");
        Assert.assertEquals(registrationform.Day.getAttribute("id"),"day");
        Assert.assertEquals(registrationform.Month.getAttribute("id"),"month");
        Assert.assertEquals(registrationform.Year.getAttribute("id"),"year");
        Assert.assertEquals(registrationform.Female.getAttribute("value"),"1");
        Assert.assertEquals(registrationform.Male.getAttribute("value"),"2");
        Assert.assertEquals(registrationform.Custom.getAttribute("value"),"-1");





    }
    @Test (priority = 2)
    public void checkRegistrationWithValidEmailAddressForMaleAccount(){

        registrationform.sendData(RegistrationData,"Email","RegWithMale","True");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Assert.assertEquals(registrationform.REGFName.getText(),registrationform.REGUName);
        Assert.assertEquals(registrationform.REGMSG.getText(),"Complete these steps in the next 30 days to make sure that you can use this account.");


    }
    @Test (priority = 3)
    public void checkRegistrationWithValidEmailAddressForFemaleAccount(){

        registrationform.sendData(RegistrationData,"Email","RegWithFemale","True");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Assert.assertEquals(registrationform.REGFName.getText(),registrationform.REGUName);
        Assert.assertEquals(registrationform.REGMSG.getText(),"Complete these steps in the next 30 days to make sure that you can use this account.");


    }

    @Test (priority = 4)
    public void checkRegistrationWithValidEmailAddressForCustomGender(){

        registrationform.sendData(RegistrationData,"Email","RegWithCustom","True");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Assert.assertEquals(registrationform.REGFName.getText(),registrationform.REGUName);
        Assert.assertEquals(registrationform.REGMSG.getText(),"Complete these steps in the next 30 days to make sure that you can use this account.");


    }
    @Test (priority = 5)
    public void checkRegistrationWithValidMobile(){

        registrationform.sendData(RegistrationData,"Phone","RegWithMale","True");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Assert.assertEquals(registrationform.REGFName.getText(),registrationform.REGUName);
        Assert.assertEquals(registrationform.REGMSG.getText(),"Complete these steps in the next 30 days to make sure that you can use this account.");


    }

    @Test (priority = 6)
    public void checkRegistrationWithinValidEmailAddress(){

        registrationform.sendData(RegistrationData,"InvalidEmail","RegWithMale","True");
        Assert.assertEquals(registrationform.WrongEmailorPhone.getText(),"Please enter a valid mobile number or email address.");


    }

    @Test (priority = 7)
    public void checkRegistrationWithInValidPhoneNumber(){

        registrationform.sendData(RegistrationData,"InvalidPhoneNumber","RegWithMale","True");
        Assert.assertEquals(registrationform.WrongEmailorPhone.getText(),"Please enter a valid mobile number or email address.");


    }


    @Test (priority = 8)
    public void checkMismatchBetweenRegMailAndConfirmationMail(){

        registrationform.sendData(RegistrationData,"MailMisMatch","RegWithMale","True");
        Assert.assertEquals(registrationform.WrongEmailorPhone.getText(),"Your emails do not match. Please try again.");


    }

    @Test (priority = 9)
    public void checkRegWithoutAddAnyInput(){

        registrationform.sendData(RegistrationData,"MailMisMatch","RegWithMale","False");
        Assert.assertEquals(registrationform.WrongEmailorPhone.getText(),"What's your name?");


    }













}
