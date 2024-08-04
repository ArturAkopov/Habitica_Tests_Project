package helpers;

import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static spec.HabiticaSpec.requestSpec;
import static spec.HabiticaSpec.responseSpec200;

public class Browserstack {
    static final AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());

    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given(requestSpec)
                .auth().basic(config.browserstackUser(), config.browserstackPassword())
                .get(url)
                .then()
                .spec(responseSpec200)
                .extract().path("automation_session.video_url");
    }
}
