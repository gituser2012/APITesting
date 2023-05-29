import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


public class RestAssuredRequestSpecification {
    private ResponseSpecification responseSpecification = new ResponseSpecBuilder().
                                                          expectStatusCode(404).
                                                          build();
    @BeforeClass
    public void setUp(){
        RestAssured.requestSpecification = new RequestSpecBuilder().
                                           setBaseUri("https://api.zippopotam.us/").
                                           setContentType("application/json").build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().
                                            expectContentType("application/json").
                                            expectResponseTime(lessThan(3000L)).build();
    }
    @Test
    public void verifyStatusCode(){

                get("us/90210").
                then().
                 assertThat().
                 statusCode(200);
    }

    @Test
    public void verifyBody(){

               get("us/90210").
                then().
                assertThat().
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

                get("us/90").
                then().spec(responseSpecification);
    }

    @Test
    public void verifyResponseType() {
          get("us/90210").
                then().
                assertThat().
                statusCode(200).
                contentType(equalTo("application/json"));
    }
    
}
