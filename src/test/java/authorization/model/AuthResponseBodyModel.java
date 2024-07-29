package authorization.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AuthResponseBodyModel {
    private Boolean success;
    private UserData data;
    private String appVersion;
    private String error;
    private String message;
    private ArrayList<Error> errors;


    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Error {
        private String message;
        private String param;
        private String value;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class UserData {
        private String id;
        private String apiToken;
        private Boolean newUser;
        private String username;
    }
}

