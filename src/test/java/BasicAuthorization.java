import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicAuthorization {
    @Test
    public void baseAuth(){
        given().auth().
                basic("postman","password").
        when().
                get("https://postman-echo.com/basic-auth").
        then().
                statusCode(200);
    }
}
