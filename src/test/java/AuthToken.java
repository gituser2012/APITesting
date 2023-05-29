import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthToken {
    @BeforeClass
    public void setUp(){
        RestAssured.requestSpecification = new RequestSpecBuilder().
                                           setBaseUri("https://api.football-data.org/v4/teams").
                                            addHeader("X-Auth-Token","ae587a9555d549839688d4e8c3f0b449").build();
    }

    @Test
    public void getMethod(){
        given().get("/90").then().statusCode(200);
    }
}
