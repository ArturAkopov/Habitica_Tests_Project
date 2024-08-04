package config;

import org.aeonbits.owner.Config;


public interface APIConfig extends Config {

    @Key("baseURI")
    @DefaultValue("https://habitica.com")
    String baseURI();

    @Key("basePath")
    @DefaultValue("/api/v4")
    String basePath();
}