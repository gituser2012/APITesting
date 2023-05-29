import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

public class ApiKeyAuth {
    @BeforeClass
    public void setUp(){
        RestAssured.requestSpecification = new RequestSpecBuilder().
                                      setBaseUri("https://api.football-data.org/v4/teams").
                                      setContentType("application/json").
                                      addHeader("X-Auth-Token","ae587a9555d549839688d4e8c3f0b449").
                                       build();
    }

    @Test
    public void verifyStatusCode(){
        get().then().statusCode(200);
    }

    @Test
    public void testCase2(){
        Response response = get("/5");//.then().body("name",equalTo("FC Bayern München"));
       JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.get("Name").toString());
    }

    @Test
    public void testCase3(){
        String str = get("/5").asString();//.then().body("name",equalTo("FC Bayern München"));
        String name = JsonPath.from(str).get("name");
        System.out.println(name);
    }
    @Test
    public void testCase4(){
        Response response = get("/5");//.then().body("name",equalTo("FC Bayern München"));
        System.out.println(response.path("name").equals("FC Bayern München"));
    }

    @Test
    public void testCase5(){
        Response response = get();//.then().body("name",equalTo("FC Bayern München"));
        System.out.println(response.path("teams[0].name").toString());
        System.out.println(response.path("teams[-1].name").toString());

        List<String> teamNames = response.path("teams.name");
        System.out.println(teamNames);
        int sizeOfTeam = response.path("teams.name.size()");
        System.out.println("Team size: "+ sizeOfTeam);
    }

    @Test
    public void complexData(){
        Response response = get();
        List<Map<String,?>> teamsData = response.path("teams");
//        Map<String,Object> dummy = new HashMap<>();
//        dummy.put("test",5);
//        dummy.put("fger","sfsf");
      //  System.out.println(response.getBody().prettyPrint());
//        System.out.println(teamsData.size());
//        for(int i=0;i<teamsData.size();i++){
//            System.out.println("entry: "+ teamsData.get(i).entrySet());
//            System.out.println("values: " + teamsData.get(i).values());
//            System.out.println("keys: "+teamsData.get(i).keySet());
//        }
        System.out.println("shortname which match Karlsruhe: "+ response.path("teams.find{it.name='Karlsruhe'}"));
    }

    @Test
    public void squadTest(){
        Response response =get("/66");

        System.out.println("Position in GoalKeeper:  "+response.path("squad.findAll{ it.position == 'Goalkeeper'}"));
        System.out.println("id less than 3500: "+response.path("squad.findAll{it.id<3500}.id"));
        System.out.println("MAx id: "+ response.path("squad.max{it.id}"));
        System.out.println("Min id: "+ response.path("squad.min{it.id}"));
        System.out.println("Position in GoalKeeper and spain:  "+response.path("squad.findAll{ it.position == 'Goalkeeper'}.find{ it.nationality == 'Spain'}"));
    }

}
