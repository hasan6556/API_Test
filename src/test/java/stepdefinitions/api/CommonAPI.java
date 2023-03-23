package stepdefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import utilities.ConfigReader;

import static hooks.api.HooksAPI.*;
import static io.restassured.RestAssured.given;

public class CommonAPI {


    public static String fullPath;
    Response response;


    @Given("Api user can set {string} thepath parameters")
    public void api_user_can_set_thepath_parameters(String rawPath) {
        //  spec.pathParams("pp1","api","pp2","login");
        //  Response response = given().when().get("{pp1}/{pp2}");

        String [] paths = rawPath.split(",");
        StringBuilder tempPath = new StringBuilder("{");

        for (int i = 0; i < paths.length; i++) {
            String key = "pp" + i;
            String value = paths[i].trim();
            HooksAPI.spec.pathParam(key,value);

            tempPath.append(key + "}/{");
        }
        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));

        System.out.println("tempPath = " + tempPath);

        fullPath = tempPath.toString();


    }


    @Given("Api user can logs email  and  password")
    public void api_user_can_logs_email_and_password() {
        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");

        /*
        {
             "email": "admin@gmail.com",
              "password": "123123123"
        }
         */

        JSONObject reqBody = new JSONObject();

        reqBody.put("email",email);
        reqBody.put("password",password);

        response = given()
                .contentType(ContentType.JSON)
                .spec(HooksAPI.spec)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

    @Given("Api user save response")
    public void api_user_save_response() {
        response = given()
                .headers("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .spec(HooksAPI.spec)
                .when()
                .get(fullPath);

        response.prettyPrint();
    }
}



/*package stepdefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import utilities.ConfigReader;

import static hooks.api.HooksAPI.*;
import static io.restassured.RestAssured.given;

public class CommonAPI {



    public static String fullPath;
    Response response;


    @Given("Api user can set {string} thepath parameters")
    public void api_user_can_set_thepath_parameters(String rowPath) {
        String[] paths=rowPath.split(",");
        StringBuilder tempPath = new StringBuilder("{");

        for (int i = 0; i < paths.length ; i++) {
            String key="pp"+i;
            String value=paths[i].trim();
            spec.pathParam(key,value);
            tempPath.append(key+"}/{");



        }

        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));
        System.out.println("tempPath = " + tempPath);
        fullPath=tempPath.toString();


    }


    @Given("Api user can logs email  and  password")
    public void api_user_can_logs_email_and_password() {
        String email =ConfigReader.getProperty("email");
        String password =ConfigReader.getProperty("password");
        JSONObject  reqBody=new JSONObject();

        reqBody.put("email", email);
        reqBody.put("password", password);

        response=given().contentType(ContentType.JSON).spec(spec).when().body(reqBody.toString()).post(fullPath);
        response.prettyPrint();
    }

    @Given("Api user save response")
    public void api_user_save_response() {
        response=given().headers("Authorization","Bearer"+ token).contentType(ContentType.JSON).
                spec(spec).when().get(fullPath);
        response.prettyPrint();
    }
}*/
