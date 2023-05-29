import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.DataProvider;

public class DataProviderDemo {
    @BeforeClass
    public void setUp(){
        RestAssured.requestSpecification = new RequestSpecBuilder().
                                           setBaseUri("https://api.zippopotam.us/").
                                            setContentType("application.json").build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().
                                             expectStatusCode(200).
                                            build();
    }

    @Test( dataProvider= "dataPro")
    public void verifyState1(String countryCode,String zipCode,String country){
        given().
                pathParams("countryCode",countryCode).
                pathParams("zipCode",zipCode).
        when().
                get("{countryCode}/{zipCode}").
                then().statusCode(200).body("country",equalTo(country));
    }

    @DataProvider(name="dataPro")
    public Object[][] test(){
      return new Object[][]{
              {"AD","AD100","Andorra"},
              {"CH","1000","Switzerland"},
              {"DE","01067","Germany"}

      };
    }
}
