package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:realMobile.properties"
})
public interface RealMobileConfig extends Config {

    String deviceName();

    String mobileUrl();

    String appPackage();

    String appActivity();

}
