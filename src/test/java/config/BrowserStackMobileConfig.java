package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:browserstackMobile.properties"
})
public interface BrowserStackMobileConfig extends Config {


    String browserstackMobileApp();

    String browserstackDeviceName();

    String browserstackOsVersion();

    String browserstackProject();

    String browserstackBuild();

    String browserstackName();

    String browserstackLanguage();

    String browserstackLocale();

    String remoteUrl();

}
