import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class servicesTestCases {



    @Test
        void validateGetALLVersions(){

            given().get("http://localhost:3030/services").then().assertThat().statusCode(200);

        }


        @Test
        void validateServicesData(){

            given().get("http://localhost:3030/services").then().body("data.name[0]",equalTo("Geek Squad Services"));

        }

        @Test
        void  checkAddNewServices() {
            JSONObject request =new JSONObject();
            Faker faker = new Faker();
            String Username = faker.name().username();
            request.put("name",Username);
            System.out.println(request.toJSONString());
            given().baseUri("http://localhost:3030/services")
                    .contentType(ContentType.fromContentType("application/json")).
                    body(request.toJSONString())
                    .when().post().then().assertThat().log().all()
                    .statusCode(201).body("name",equalTo(Username));}



        @Test
        void CheckAddNewStoreWithoutSendingAnyParameters() {
            JSONObject request = new JSONObject();
            given().baseUri("http://localhost:3030/services")
                    .contentType(ContentType.fromContentType("application/json")).
                    body(request.toJSONString()).post().then().statusCode(400);
        }


        @Test
        void  CheckUpdateOnNewStore() {
            JSONObject request =new JSONObject();
            Faker faker = new Faker();
            String Username = faker.name().username();
            request.put("name",Username);
            System.out.println(request.toJSONString());
            given().baseUri("http://localhost:3030/services/3")
                    .contentType(ContentType.fromContentType("application/json")).
                    body(request.toJSONString())
                    .when().patch().then().assertThat().log().all()
                    .statusCode(200).body("name",equalTo(Username));}

        @Test
        void checkDeleteForSpecificStore() {

            Response response =given().get("http://localhost:3030/services");
            List<Object> idList = response.jsonPath().getList("data.id");
            System.out.println(idList.get(3));
            baseURI="http://localhost:3030/services";
            given().delete(idList.get(3).toString()).then().log().all().statusCode(200);
            given().get(idList.get(3).toString()).then().statusCode(404);//185230

        }
    }

















