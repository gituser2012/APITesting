import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class RequestSpecificationBuilder {

    private RequestSpecification requestSpecification = new RequestSpecBuilder().
                                                        setBaseUri("https://api.zippopotam.us/").
                                                        build();
    private ResponseSpecification responseSpecification = new ResponseSpecBuilder().
                                                          expectStatusCode(200).
                                                          expectContentType("application/json").
                                                            expectResponseTime(lessThan(400L)).
                                                          build();
    @Test
    public void verifyStatusCode(){
        given().
                spec(requestSpecification).
        when().
                get("us/90210").
        then().
               spec(responseSpecification);
    }

    @Test
    public void verifyBody(){
        given().
                spec(requestSpecification).
        when().
                get("us/90210").
         then().
                assertThat().
                spec(responseSpecification).
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
               spec(requestSpecification).
        when().
                get("us/90").
                then().
                assertThat().
                statusCode(404);
    }

    @Test
    public void verifyResponseType() {
        given().
                spec(requestSpecification).
        when().
                get("us/90210").
        then().
                assertThat().
                statusCode(200).
                contentType(equalTo("application/json"));
    }
}
