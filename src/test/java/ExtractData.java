import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class ExtractData {
    @BeforeClass
    public void setUp(){
        RestAssured.requestSpecification = new RequestSpecBuilder().
                                           setBaseUri("https://api.zippopotam.us/").build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().
                                            expectStatusCode(200).build();
    }

    @Test
    public void extractResponse(){
       Response response = get("us/90210").then().extract().response();
        System.out.println(response.path("country").equals("United States"));
    }
}
