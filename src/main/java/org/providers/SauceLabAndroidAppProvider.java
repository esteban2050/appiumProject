package org.providers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class SauceLabAndroidAppProvider implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        //Recordar: setPlatform y setAutomation.. no son necesarias, ya vienen por defecto al crear la instancia
        UiAutomator2Options options = new UiAutomator2Options();
        options
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")//O usar este enum: AutomationName.ANDROID_UIAUTOMATOR2
                .setDeviceName("automation")
                .setApp(System.getProperty("user.dir") + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");
                //.setNoReset(false); //true: no instalaria la app si ya esta presente en el cel. false: la va instalar cada vez que se ejcute
        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
