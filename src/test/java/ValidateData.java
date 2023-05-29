import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.InputStream;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ValidateData {
    @BeforeClass
    public void setUp() {
        RestAssured.requestSpecification = new RequestSpecBuilder().
                                            setBaseUri("https://api.football-data.org/v4/teams/90").
                                            addHeader("X-Auth-Token","ae587a9555d549839688d4e8c3f0b449").build();
    }

    @Test
    public void testCase1() {
        given().get().then().assertThat().body("id",equalTo(90));
    }

    @Test
    public void testCase2() {
        Response response = get();
        Assert.assertEquals((Integer) response.path("id"),90);
    }

    @Test
    public void testCase3() {
        Response response = get();
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals((Integer)jsonPath.get("id"),90);
    }

//    @Test
//    public void testCase4(){
//        String response = get().toString();
//        System.out.println();
//      soutJsonPath.from(response).get("id");
//        Assert.assertEquals(idEmp,90);
//    }

}
