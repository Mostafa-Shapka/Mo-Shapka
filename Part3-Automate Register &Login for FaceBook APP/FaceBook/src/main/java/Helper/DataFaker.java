package Helper;

import com.github.javafaker.Faker;

import java.io.ObjectOutputStream;
import java.time.Month;

public class DataFaker  {


        Faker faker = new Faker();
        public String UserName = faker.name().firstName();
        public String SurName = faker.name().lastName();
        public String Password = faker.internet().password();

        public String Mobile = faker.numerify("011########");
        public String Email = UserName + SurName+ "@gmail.com";

        public String RegUserName=UserName;






}







