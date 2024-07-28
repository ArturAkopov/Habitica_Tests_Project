package authorization.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserData {
    private String id;
    private String apiToken;
    private Boolean newUser;
    private String username;
}
