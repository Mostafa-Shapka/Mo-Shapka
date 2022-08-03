
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class ProductTestCases {

    @Test
    void validateGetResponseforProducts(){

      given().get("http://localhost:3030/products/").then().assertThat().statusCode(200);

    }

    @Test
    void validateProductsData(){

        given().get("http://localhost:3030/products/43900").then().body("name",equalTo("Duracell - AAA Batteries (4-Pack)"))
                .and().body("type", equalTo("HardGood"));


    }

    @Test
    void  checkPostOnProductList() {
        JSONObject request =new JSONObject();
        request.put("name","TestMostafa2");
        request.put("type","Test");
        request.put("price",0);
        request.put("shipping",0);
        request.put("upc","TestMostafa");
        request.put("description","Test");
        request.put("manufacturer","Test");
        request.put("model","Test");
        request.put("image","Test");

        System.out.println(request.toJSONString());

    given().baseUri("http://localhost:3030/products")
                .contentType(ContentType.fromContentType("application/json")).
    body(request.toJSONString())
            .when().post().then().assertThat()
                .statusCode(201).body("name",equalTo("TestMostafa2"))

    ;}

//No PUT requests according to implementation
    @Test
    void  checkSendPUTRequestOnProductList(){
        JSONObject request =new JSONObject();
        request.put("name","TestMostafa1");
        request.put("type","Test");
        request.put("price","0");
        request.put("shipping","0");
        request.put("upc","TestMostafa");
        request.put("manufacturer","Test");
        request.put("manufacturer","Test");
        request.put("model","Test");
        request.put("URL","Test");
        request.put("image","Test");
        given().baseUri("http://localhost:3030/products/9132294")
                .contentType(ContentType.JSON).
                body(request.toJSONString())
                .when().put().then().assertThat()
                .statusCode(400)

        ;}

    @Test
    void  checkSendPatchRequestToMakeUpdateOnSpecificProduct(){
        JSONObject request =new JSONObject();
        request.put("name","TestMostafa1");
        request.put("type","Test");
        request.put("price","0");
        request.put("shipping","0");
        request.put("upc","TestMostafa");
        request.put("description","Test");
        request.put("manufacturer","Test");
        request.put("model","Test");
        request.put("URL","Test");
        request.put("image","Test");
        given().baseUri("http://localhost:3030/products/9132294")
                .contentType(ContentType.JSON).
                body(request.toJSONString())
                .when().patch().then().assertThat()
                .statusCode(200).body("name",equalTo("TestMostafa1"));}
    @Test

    void checkSendDeleteToProductList(){

        Response response =given().get("http://localhost:3030/products/");
        List<Object> idList = response.jsonPath().getList("data.id");
        System.out.println(idList.get(3));
        baseURI="http://localhost:3030/products/";
        given().delete(idList.get(3).toString()).then().statusCode(200);
        given().get(idList.get(3).toString()).then().statusCode(404);//185230
    }
}
