import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.mozilla.javascript.serialize.ScriptableOutputStream;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;


public class PostGetterSetter {
    @BeforeClass
    public void Setup(){
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://petstore.swagger.io/v2/")
                . setContentType("application/json")
                .build();
    }

    @Test
    public void postMethod(){
        Category category = new Category(12345,"dog");
        Pet pet = new Pet(32145,"whitie",category,"available");

        Response response = given().body(pet).when().post();
        System.out.println(response.statusCode());
    }

    @Test
    public void getMethod(){
        Pet pet1 = get("https://petstore.swagger.io/v2/pet/34567").as(Pet.class);
        System.out.println(pet1.getId());
    }

    @Test
    public void postRequest(){
        Category category = new Category(532,"whitee");
        Pet pet = new Pet(89765,"dog",category,"pending");
        Response response = given().body(pet).contentType("application/json").when()
                .post("pet/");//.then().assertThat().statusCode(200);
        System.out.println(response.statusCode());
        System.out.println(response.getBody().prettyPrint());
        System.out.println(response.path("id").toString());
    }

    @Test
    public void getResponse(){
        Pet pet = get("pet/89765").as(Pet.class);
        System.out.println(pet.getStatus());
    }

    @Test
    public void putRequest(){

        Category category = new Category(532,"whitee");
        Pet pet = new Pet(89765,"dog",category,"available");
        Response response = given().body(pet).contentType("application/json").when()
                .put("pet");//.then().assertThat().statusCode(200);
        System.out.println(response.statusCode());
        System.out.println(response.getBody().prettyPrint());
        System.out.println(response.path("status").toString());
    }


}
