import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


import static io.restassured.RestAssured.*;

public class MapForAllMethods {
    @BeforeClass
    public void setUp(){
        RestAssured.requestSpecification = new RequestSpecBuilder().
                                            setBaseUri("https://petstore.swagger.io/v2/pet/").
                                            setContentType("application/json").
                                            build();
    }

    @Test
    public void allMethods(){
        Map<String,Object> category = new HashMap<>();
        category.put("id","000");
        category.put("name","test01");
        Map<String,Object> pet = new HashMap<>();
        pet.put("id", "2345");
        pet.put("name","dog");
        pet.put("status","available");
        pet.put("category",category);

       Response response = given().body(pet).when().post();
       String newId = response.path("id").toString();
        System.out.println(newId);

       get(newId).then().assertThat().statusCode(200);

       pet.put("status","pending");
       pet.put("id","2345");
       Response putResponse = given().body(pet).when().put();
        System.out.println(putResponse.path("status").toString());

        get(newId).then().assertThat().statusCode(200);

         delete("2345").then().statusCode(200);
        get(newId).then().assertThat().statusCode(404);


    }
}
