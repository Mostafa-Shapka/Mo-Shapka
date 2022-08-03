import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StoresTestCases {




    @Test
    void validateGetALLStoresData(){

        given().get("http://localhost:3030/services").then().assertThat().statusCode(200);

    }


    @Test
    void validateStoresData(){

        given().get("http://localhost:3030/stores/4").then().body("name",equalTo("Minnetonka"))
                .and().body("type", equalTo("BigBox"));
    }

    @Test
    void  checkAddNewStore() {
        JSONObject request =new JSONObject();
        Faker faker = new Faker();
        String Username = faker.name().username();
        request.put("name",Username);
        request.put("type", faker.name().username());
        request.put("address",faker.name().username());
        request.put("address2", faker.name().username());
        request.put("city", faker.name().username());
        request.put("state",faker.name().username());
        request.put("zip", faker.name().username());
        request.put("lat", 0);
        request.put("lng", 0);
        request.put("hours", faker.name().username());
        System.out.println(request.toJSONString());
        given().baseUri("http://localhost:3030/stores/")
                .contentType(ContentType.fromContentType("application/json")).
                body(request.toJSONString())
                .when().post().then().assertThat().log().all()
                .statusCode(201).body("name",equalTo(Username));}


    @Test
    void CheckAddNewStoreWithoutSendLatAndLong(){
    JSONObject request =new JSONObject();
    Faker faker = new Faker();
    String Username = faker.name().username();
    request.put("name",Username);
    request.put("type", faker.name().username());
    request.put("address",faker.name().username());
    request.put("address2", faker.name().username());
    request.put("city", faker.name().username());
    request.put("state",faker.name().username());
    request.put("zip", faker.name().username());
    request.put("hours", faker.name().username());
    System.out.println(request.toJSONString());
    given().baseUri("http://localhost:3030/stores/")
            .contentType(ContentType.fromContentType("application/json")).
            body(request.toJSONString())
            .when().post().then().assertThat().log().all()
            .statusCode(201).body("name",equalTo(Username));}
    @Test
    void CheckAddNewStoreWithoutSendingAnyParamters() {
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
        request.put("type", faker.name().username());
        request.put("address",faker.name().username());
        request.put("address2", faker.name().username());
        request.put("city", faker.name().username());
        request.put("state",faker.name().username());
        request.put("zip", faker.name().username());
        request.put("lat", 0);
        request.put("lng", 0);
        request.put("hours", faker.name().username());
        System.out.println(request.toJSONString());
        given().baseUri("http://localhost:3030/stores/6")
                .contentType(ContentType.fromContentType("application/json")).
                body(request.toJSONString())
                .when().patch().then().assertThat().log().all()
                .statusCode(200).body("name",equalTo(Username));}

    @Test
    void checkDeleteForSpecificStore() {

        Response response =given().get("http://localhost:3030/stores/");
        List<Object> idList = response.jsonPath().getList("data.id");
        System.out.println(idList.get(3));
        baseURI="http://localhost:3030/stores";
        given().delete(idList.get(3).toString()).then().log().all().statusCode(200);
        given().get(idList.get(3).toString()).then().statusCode(404);//185230

    }
}











