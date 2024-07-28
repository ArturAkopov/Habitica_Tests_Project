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


}

