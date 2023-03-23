package stepdefinitions.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class UserUpdateStepDefs {
    private Response response;
    private RequestSpecification request;
    private final String USER_ENDPOINT = "https://example.com/users";
    private Map<String, String> user = new HashMap<>();

    @Given("kullanıcı kimlik bilgileri verilmiştir")
    public void set_user_credentials() {
        user.put("username", "johnsmith");
        user.put("password", "password123");
    }

    @Given("güncellenecek profil bilgileri verilmiştir")
    public void set_updated_user_profile() {
        user.put("name", "John");
        user.put("surname", "Smith");
        user.put("email", "johnsmith@example.com");
    }

    @When("kullanıcı profil bilgilerini günceller")
    public void update_user_profile() {
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(user);
        response = request.put(USER_ENDPOINT + "/" + user.get("username"));
    }

    @Then("güncelleme başarılı bir şekilde gerçekleşir")
    public void verify_user_profile_update() {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        Map<String, String> updatedUser = response.jsonPath().getMap("$");
        Assert.assertEquals(updatedUser.get("name"), user.get("name"));
        Assert.assertEquals(updatedUser.get("surname"), user.get("surname"));
        Assert.assertEquals(updatedUser.get("email"), user.get("email"));
    }
}