import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Categories {


    @Test
    void validateGetResponseforProducts(){

        given().get("http://localhost:3030/categories/").then().assertThat().statusCode(200);

    }


    @Test
    void validateCategoriesData(){

        given().get("http://localhost:3030/categories/abcat0010000").then().body("name",equalTo("Gift Ideas"))
                .and().body("subCategories.name[0]", equalTo("Unique Gifts"));
    }

    @Test
    void  checkAddNewCategories() {
        JSONObject request =new JSONObject();
        Faker faker = new Faker();
        String name = faker.name().username();
        String id= faker.idNumber().valid();
        request.put("name",name);
        request.put("id",id);
        System.out.println(request.toJSONString());
        given().baseUri("http://localhost:3030/categories/")
                .contentType(ContentType.fromContentType("application/json")).
                body(request.toJSONString())
                .when().post().then().assertThat()
                .statusCode(201).body("name",equalTo(name))
        ;}
    @Test
    void  checkAddCategoryWithTheSameID() {
        JSONObject request =new JSONObject();
        String name = "Gift Ideas";
        String id= "abcat0010000";
        request.put("name",name);
        request.put("id",id);
        System.out.println(request.toJSONString());
        given().baseUri("http://localhost:3030/categories/pcmcat84000050002")
                .contentType(ContentType.fromContentType("application/json")).
                body(request.toJSONString())
                .when().post().then().assertThat()
                .statusCode(404);

    }
    @Test
    void  checkUpdateForExistingCategory() {
        JSONObject request =new JSONObject();
        Faker faker = new Faker();
        String name = faker.name().username();
        request.put("name",name);
        System.out.println(request.toJSONString());
        given().baseUri("http://localhost:3030/categories/pcmcat84000050002")
                .contentType(ContentType.fromContentType("application/json")).
                body(request.toJSONString())
                .when().patch().then().assertThat()
                .statusCode(200).body("name",equalTo(name))
        ;}

    @Test
    void checkSendDeleteANYExitingCategory(){

        Response response =given().get("http://localhost:3030/categories/");
        List<Object> idList = response.jsonPath().getList("data.id");
        System.out.println(idList.get(3));
        baseURI="http://localhost:3030/categories/";
        given().delete(idList.get(3).toString()).then().log().all().statusCode(200);
        given().get(idList.get(3).toString()).then().statusCode(404);
    }



}
