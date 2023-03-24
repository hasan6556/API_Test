package stepdefinitions.api;

import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;

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
        String url="https://trendlifebuy.com/api/register";
        //2 requestBody yi hazirla
        /*
        curl --location 'https://trendlifebuy.com/api/register' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
  "first_name": "registerApi",
  "last_name": "TestApi",
  "email": "api@api.com",
  "password": "123123123",
  "password_confirmation": "123123123",
  "user_type": "customer",
  "referral_code": "0101010101"
}'
         */

        JSONObject reqBody= new JSONObject();
        reqBody.put("first_name", "registerApi");
        reqBody.put("last_name", "TestApi");
        reqBody.put("email", "admin039@trendlifebuy.com");
        reqBody.put("password", "Trendlife123");
        reqBody.put("password_confirmation", "Trendlife123");
        reqBody.put("user_type", "customer");
        reqBody.put("referral_code", "0101010101");

        response=given().
              headers("Authorization", "Bearer " + "858|olfPgnQrjyTcfvFaUlo8sZEA3rlyv7eoIMsyFril").
              contentType(ContentType.JSON).
              when().
              body(reqBody.toString()).
              post(url);
      response.prettyPrint();

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

    }

}
