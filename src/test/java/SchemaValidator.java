import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.proxy;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidator {
    @BeforeClass
    public void setUp(){
        RestAssured.requestSpecification = new RequestSpecBuilder().
                                         setBaseUri("https://petstore.swagger.io/v2/pet/").
                                         setContentType("application/json").
                                         build();
    }

    @Test
    public void getMethod(){
       when().get("34567/").then().body(matchesJsonSchemaInClasspath("PetSchema.json"));
    }
}
