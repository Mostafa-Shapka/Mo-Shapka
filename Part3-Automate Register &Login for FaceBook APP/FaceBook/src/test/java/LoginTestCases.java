import Helper.FileReader;
import com.google.gson.JsonObject;
import javafx.scene.layout.Priority;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

public class LoginTestCases extends DriverSetup {

    LoginPage loginPage;
    JsonObject LoginData;

    HomePage homePage;
    RestPasswordPage restPasswordPage;
    RegistrationForm registrationForm;

    @BeforeClass
    public void setupData() throws FileNotFoundException {
        FileReader ReadData =new FileReader();
        LoginData=ReadData.readDate("src/main/resources/LoginData.json");
    }


    @BeforeMethod
    public  void faceBookURL(){
        driver.manage().deleteAllCookies();
        driver.get("https://www.facebook.com/");
    }

    @BeforeClass
    public void initializations () {

        loginPage=new LoginPage(driver);
        homePage=new HomePage(driver);
        registrationForm=new RegistrationForm(driver);
        restPasswordPage=new RestPasswordPage(driver);}

    @Test
    public void checkLoginScreenUI(){
        Assert.assertEquals(loginPage.EmailORPhoneNumber.getAttribute("name"),"email");
        Assert.assertEquals(loginPage.Password.getAttribute("name"),"pass");
        Assert.assertEquals(loginPage.LoginBTN.getAttribute("name"),"login");
        Assert.assertEquals(loginPage.ForgetPassword.getText(),"Forgotten password?");
        Assert.assertEquals(loginPage.CreateAccountBTN.getText(),"Create New Account");


    }

    @Test (priority = 1)

    public void checkLoginWithValidData(){

        loginPage.sendLoginData(LoginData,"ValidLogin");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Assert.assertEquals(homePage.WelcomeMSG.getText(),"Welcome to Facebook, Mostafa");
    }

    @Test (priority = 2)

    public void checkLoginWithInValidData(){
        loginPage.sendLoginData(LoginData,"InvalidLogin");
        Assert.assertEquals(loginPage.InvalidLogInMSG.getText(),"The password that you've entered is incorrect. Forgotten password?");

    }

    @Test (priority = 3)

    public void checkForgetPasswordDirection(){

        loginPage.sendLoginData(LoginData,"ForgetPassword");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Assert.assertEquals(restPasswordPage.RestPassword.getText(),"Find Your Account");


    }
    @Test (priority = 4)
    public void checkLoginWithoutAnyRequiredInput(){
        loginPage.sendLoginData(LoginData,"NoInput");
        Assert.assertEquals(loginPage.InvalidLogInMSG.getText(),"The email address or mobile number you entered isn't connected to an account. Find your account and log in.");

    }
    @Test (priority = 5)
    public void checkDirectionToREGPage(){
        loginPage.createNewAccount();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Assert.assertEquals(registrationForm.Signup.getText(),"Sign Up");


    }

















}
