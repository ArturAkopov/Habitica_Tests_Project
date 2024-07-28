package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:api.properties"
})
public interface APIConfig extends Config {
    @DefaultValue("https://habitica.com")
    String baseURI();
    @DefaultValue("/api/v4")
    String basePath();
}