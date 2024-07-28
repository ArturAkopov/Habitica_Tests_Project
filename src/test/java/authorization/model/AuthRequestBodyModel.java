package authorization.model;

import lombok.Data;

@Data
public class AuthRequestBodyModel {
    private String username;
    private String password;
}
