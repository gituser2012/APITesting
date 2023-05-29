import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class BasicTestForStatusCode {
    @Test
    public void postMethod(){
        given().
                baseUri("https://api.zippopotam.us/").
                when().
                get("us/90210").
                then().
                assertThat().statusCode(200);
    }
    @Test
    public void verifyStatusJavaCode() throws IOException {
        URL url = new URL("https://api.zippopotam.us/");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
       if(httpURLConnection.getResponseCode() == 200){
           System.out.println("Success");
       }
    }
}
