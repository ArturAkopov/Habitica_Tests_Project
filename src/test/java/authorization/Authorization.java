package authorization;

import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;
import authorization.model.AuthRequestBodyModel;
import authorization.model.AuthResponseBodyModel;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static spec.HabiticaSpec.requestSpec;
import static spec.HabiticaSpec.responseSpec200;

public class Authorization {

    public static AuthResponseBodyModel getAuthResponse() {

        AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());
        AuthRequestBodyModel request = new AuthRequestBodyModel();
        request.setUsername(authConfig.login());
        request.setPassword(authConfig.password());
        return given(requestSpec)
                .body(request)
                .when()
                .post("/user/auth/local/login")
                .then()
                .spec(responseSpec200)
                .extract().as(AuthResponseBodyModel.class);

    }

    public static void setAuthDataInLocalStorage(AuthResponseBodyModel authResponse) throws JsonProcessingException {
        HabitMobileSettings habitMobileSettings =
                new HabitMobileSettings(authResponse.getData().getId(), authResponse.getData().getApiToken());
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String authData = mapper.writeValueAsString(habitMobileSettings);
        open("/static/img/bits.d0926ee2.svg");
        Selenide.localStorage().setItem("habit-mobile-settings", authData);
    }
}
