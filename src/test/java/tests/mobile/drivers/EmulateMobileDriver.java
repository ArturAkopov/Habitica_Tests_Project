package tests.mobile.drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.EmulateMobileConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;


public class EmulateMobileDriver implements WebDriverProvider {

    private static final EmulateMobileConfig config =
            ConfigFactory.create(
                    EmulateMobileConfig.class,
                    System.getProperties()
            );


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        return getEmulateDriver();
    }

    public AndroidDriver getEmulateDriver() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setPlatformVersion(config.platformVersion())
                .setDeviceName(config.deviceName())
                .setApp(getAppPath())
                .setAppPackage(config.appPackage())
                .setAppActivity(config.appActivity());

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL(config.mobileUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAppPath() {
        String appUrl = "https://github.com/HabitRPG/habitica-android/releases/download/4.4/7971.apk";
        String appPath = "src/test/resources/apps/7971.apk";

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }

}
