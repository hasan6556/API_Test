package stepdefinitions.api;

import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.RegisterPojo;
import pojos.RegisterResponsePojo;

import static io.restassured.RestAssured.given;

public class MyStepdefs  {

    public static String token;
    public static Response response;
    @Given("User takes the bearer token")
    public void user_takes_the_bearer_token() {
        //1-url hazirla
        String url="https://trendlifebuy.com/api/login";
        //2- Request body hazirla
        /*
        {
         "email": "admin@gmail.com",
         "password": "123123123"
          }

         */
        JSONObject reqBody= new JSONObject();
        reqBody.put("email", "admin039@trendlifebuy.com");
        reqBody.put("password", "Trendlife123");

         response= given().
                contentType(ContentType.JSON).
                when().
                body(reqBody.toString()).
                post(url);

        response.prettyPrint();
        JsonPath jsonResponse=response.jsonPath();

         token=jsonResponse.getString("token");
    }


    @Given("User sets the post request body")
    public void user_sets_the_post_request_body() {
        //1 url hazirla
        String url="https://trendlifebuy.com/api/get-user";
        //2 requestBody yi hazirla
        JSONObject req = new JSONObject();
        req.put("id",1062);

        RegisterPojo registerPojos= new RegisterPojo("AKO","YOR","AKYOR",
                "bdmin039@trendlifebuy.com","787645Au76","787645Au76","customer",
                "921123123",56793472);

        response=given().
                headers("Authorization", "Bearer " + "858|olfPgnQrjyTcfvFaUlo8sZEA3rlyv7eoIMsyFril").
                contentType(ContentType.JSON).
                when().
                body(req.toString()).
                get(url);
        response.prettyPrint();

        JsonPath res= response.jsonPath();

        Assert.assertEquals(registerPojos.getFirst_name(),res.getString("user.first_name"));
        Assert.assertEquals(registerPojos.getLast_name(),res.getString("user.last_name"));
        System.out.println(res.getString("user.first_name"));

    }
    @Given("User can see the status code {int} and response message information")
    public void user_can_see_the_status_code_and_response_message_information(Integer int1) {
        response.then().
                assertThat().
                statusCode(400).
                body("message", Matchers.equalTo("duplicate username, change username please"));
    }

    @Given("User sends the post request")
    public void user_sends_the_post_request() {
        //specTrend.pathParam("pp1","register");
        String url="https://trendlifebuy.com/api/get-user";

        //2 body hazirla

      JSONObject  reqBody=  new JSONObject();
      reqBody.put("id",1062);

        //3 responsu kaydet
         response= given().
                 headers("Authorization", "Bearer " + "858|olfPgnQrjyTcfvFaUlo8sZEA3rlyv7eoIMsyFril").

                 contentType(ContentType.JSON).
                 when().
                 body(reqBody.toString()).
                 get(url);
         response.prettyPrint();

    }
    @Given("User can see what he added")
    public void user_can_see_what_he_added() {

        //1 url hazirla
        String url="https://trendlifebuy.com/api/get-user";
        //2 requestBody yi hazirla
        JSONObject req = new JSONObject();
        req.put("id",1062);



        RegisterPojo registerPojos= new RegisterPojo("AKO","YOR","AKYOR",
                "bdmin039@trendlifebuy.com","787645Au76","787645Au76","customer",
                "921123123",56793472);
        RegisterResponsePojo registerResponsePojo= new RegisterResponsePojo(registerPojos,"success");
        response=given().
                headers("Authorization", "Bearer " + "858|olfPgnQrjyTcfvFaUlo8sZEA3rlyv7eoIMsyFril").
                contentType(ContentType.JSON).
                when().
                body(req.toString()).
                get(url);
        response.prettyPrint();

        JsonPath res= response.jsonPath();

        Assert.assertEquals(registerPojos.getFirst_name(),res.getString("user.first_name"));
        Assert.assertEquals(registerPojos.getLast_name(),res.getString("user.last_name"));
        System.out.println(res.getString("user.first_name"));

        Assert.assertEquals(registerResponsePojo.getMessage(),res.getString("message"));


        System.out.println(registerResponsePojo.getMessage());
    }
    //TC_03

    @Given("User can see related data what he added")
    public void user_can_see_related_data_what_he_added() {
       //1 url hazirla
        String url="https://trendlifebuy.com/api/register";
        //req body hazirla
        RegisterPojo regBody= new RegisterPojo("Abdulcan43","YakarYakmaz43","YANBAKAR01","abdulcanyakar45@gmail.com",
        "A456765423","A456765423","customer","067857098",678573);

        // expected body olustu
        RegisterResponsePojo expBody=  new RegisterResponsePojo(regBody,"success");

        Response resbody= given().
                headers("Authorization", "Bearer " + "858|olfPgnQrjyTcfvFaUlo8sZEA3rlyv7eoIMsyFril").
                contentType(ContentType.JSON).
                when().
                body(regBody.toString()).
                post(url);

        resbody.prettyPrint();

        RegisterResponsePojo newResbody=resbody.as(RegisterResponsePojo.class);

        Assert.assertEquals(expBody.user.getFirst_name(),newResbody.getUser().getFirst_name());
        Assert.assertEquals(expBody.user.getLast_name(),newResbody.getUser().getLast_name());
        Assert.assertEquals(expBody.getUser().getEmail(),newResbody.getUser().getEmail());
        Assert.assertEquals(expBody.getUser().getPhone(),newResbody.getUser().getPhone());
        Assert.assertEquals(expBody.getUser().getUsername(),newResbody.getUser().getUsername());



    }


}
