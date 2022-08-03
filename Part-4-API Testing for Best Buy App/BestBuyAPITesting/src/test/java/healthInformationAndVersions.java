import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class healthInformationAndVersions {
    @Test
    void validateProductsData(){

        given().get("http://localhost:3030/healthcheck").then().log().all().body("documents.products",equalTo(51965))
                .and().body("documents.stores", equalTo(1564)).and().body("documents.categories"
        ,equalTo(4308));
}
    @Test
    void validateVersions(){

    given().get("http://localhost:3030/version").then().log().all().body("version",equalTo("1.1.0"));
}




}
