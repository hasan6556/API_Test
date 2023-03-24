package baseUrl;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


public class Base_URL {

    public RequestSpecification specTrend;
    @Before
    public void setUp(){
        specTrend=new RequestSpecBuilder().setBaseUri("https://trendlifebuy.com/api").build();
    }
}
