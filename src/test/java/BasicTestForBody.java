import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicTestForBody {
    @Test
    public void verifyStatusCode(){
        given().
                baseUri("https://api.zippopotam.us/").
        when().
                get("us/90210").
        then().
                statusCode(200);
    }

    @Test
    public void verifyBody(){
        given().
                baseUri("https://api.zippopotam.us/").
        when().
                get("us/90210").
         then().
                assertThat().
                statusCode(200).
                body("country",equalTo("United States")).
                body("'country abbreviation'",equalTo("US")).
                body("places[0].'place name'",equalTo("Beverly Hills")).
                body("places[0].longitude",equalTo("-118.4065")).
                body("places[0].state",equalTo("California")).
                body("places[0].'state abbreviation'",equalTo("CA")).
                body("places[0].latitude",equalTo("34.0901"));
    }

    @Test
    public void verifyNegativeCase(){
        given().
                baseUri("https://api.zippopotam.us/").
        when().
                get("us/90").
                then().
                assertThat().
                statusCode(404);
    }

    @Test
    public void verifyResponseType() {
        given().
                baseUri("https://api.zippopotam.us/").
        when().
                get("us/90210").
        then().
                assertThat().
                statusCode(200).
                contentType(equalTo("application/json"));
    }
}
