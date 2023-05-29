import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BasicAuth {
    @BeforeClass
    public void setUp() {
        RestAssured.requestSpecification = new RequestSpecBuilder().
                                             setBaseUri("https://postman-echo.com/basic-auth").build();
    }

    @Test
    public void basicAuth() {
        given().auth().basic("postman","password").when().get().then().statusCode(200);
    }
}
