import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PetDogMethods {
    @BeforeClass
    public void Setup(){
        RestAssured.requestSpecification = new RequestSpecBuilder().
                                           setBaseUri("https://petstore.swagger.io/v2").setContentType("application/json")
                                               .build();
    }

    @Test(priority = 1)
    public void getMethod(){
        get("/345667").
                then().
        assertThat().
                statusCode(200);
    }

    @Test(priority = 2)
    public void deleteMethod(){
        delete("/345667").
                then().
                assertThat().
                statusCode(200);

    }

    @Test
    public void postMethod(){
        String requestBody = "{\n" +
                "  \"id\": 111,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}" ;
                Response response = given().
                body(requestBody).
         when().
                post("https://petstore.swagger.io/v2/pet");
        System.out.println(response.statusCode());
    }
}
