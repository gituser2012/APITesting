import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PetDemo {
    @BeforeClass
    public void setUp(){
        RestAssured.requestSpecification = new RequestSpecBuilder().
                                          setBaseUri("https://petstore.swagger.io/v2/").
                                          setContentType("application/json").build();
    }
    @Test
    public void postRequest(){
        Category category = new Category(532,"whitee");
        Pet pet = new Pet(89765,"dog",category,"pending");
       Response response = given().body(pet).when()
                .post("pet/");//.then().assertThat().statusCode(200);
        System.out.println(response.statusCode());
        System.out.println(response.getBody().prettyPrint());
    }

    @Test
    public void allMethods(){
        Category category = new Category(532,"whitee");
        Pet pet = new Pet(89765,"dog",category,"pending");

        Response response = given().body(pet).contentType("application/json").when()
                .post("pet/");
        int id = response.path("id");
        get("pet/"+ id).then().assertThat().statusCode(200);

        pet.setStatus("available");
        pet.setId(id);
        response = given().body(pet).when().put("pet/");//.then().statusCode(200);
        System.out.println(response.path("status").toString());

        delete("pet/"+ id).then().assertThat().statusCode(200);
        response = get("pet/"+ id);//.then().assertThat().statusCode(404);
        System.out.println(response.statusCode());
    }

    @Test
    public void allMapMethods(){
        Map<String,Object> category = new HashMap<>();
        category.put("id",542);
        category.put("name","snoopie");
       Map<String,Object> pet = new HashMap<>();
   //    pet.put("id",425);
        pet.put("name","dog");
        pet.put("category",category);
        pet.put("status","pending");


        Response response = given().body(pet).contentType("application/json").when()
                .post("pet/");
        String id = response.path("id").toString();
        get("pet/"+ id).then().assertThat().statusCode(200);

        pet.put("status","available");
        pet.put("id",id);
        response = given().body(pet).when().put("pet/");//.then().statusCode(200);
        System.out.println(response.path("status").toString());

        delete("pet/"+ id).then().assertThat().statusCode(200);
        response = get("pet/"+ id);//.then().assertThat().statusCode(404);
        System.out.println(response.statusCode());
    }
}
