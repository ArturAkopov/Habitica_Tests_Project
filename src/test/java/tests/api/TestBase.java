package tests.api;

import config.APIConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {

    @BeforeAll
    static void setup() {
        APIConfig config = ConfigFactory.create(APIConfig.class, System.getProperties());
        RestAssured.baseURI = config.baseURI();
        RestAssured.basePath = config.basePath();
    }

}
