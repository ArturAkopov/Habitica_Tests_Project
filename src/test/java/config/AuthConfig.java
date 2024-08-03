package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:auth.properties"
})

public interface AuthConfig extends Config {
    String login();
    String email();
    String password();
    String userId();
    String apiToken();
    String browserstackUser();
    String browserstackPassword();
}
