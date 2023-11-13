package selenide.appium.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.ScreenObject;
import com.codeborne.selenide.appium.SelenideAppium;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.providers.SauceLabAndroidAppProvider;
import org.providers.SauceLabIOSAppProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import screen.HomeScreen;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.appium.SelenideAppium.$;

//Para hacer las cosas con selenid appium, no es necesario agregar la dependencia de appium al proyecto
public class withSelenideTest {

    @Test
    public void androidWithBrowserProperties() {
        SelenideAppium.launchApp();
        //With this launch the app and store the webDriver in webDriverRunner
        //WebDriverRunner.getWebDriver().findElement(AppiumBy.accessibilityId("open menu")).click();
        //Instead of that:
        $(AppiumBy.accessibilityId("open menu")).click();
    }

    @Test
    public void androidLaunchTest() {
        Configuration.browser = SauceLabAndroidAppProvider.class.getName();
        SelenideAppium.launchApp();
        HomeScreen homeScreen = ScreenObject.screen(HomeScreen.class); //Inicializar los elementos de la pagina
        homeScreen.clickProduct1()
                .checkWhetherAddToCartButtonIsPresent();
    }

    @Test
    public void IOSLaunchTest(){
        Configuration.browser = SauceLabIOSAppProvider.class.getName();
        SelenideAppium.launchApp();
        HomeScreen homeScreen = ScreenObject.screen(HomeScreen.class); //Inicializar los elementos de la pagina
        homeScreen.clickProduct1WithList()
                .checkWhetherAddToCartButtonIsPresent();
    }

    @Test
    public void clickProductCombineOS(){
        SelenideAppium.launchApp();
        HomeScreen homeScreen = ScreenObject.screen(HomeScreen.class);
        homeScreen.clickProductCombined()
                .checkWhetherAddToCartButtonIsPresent();
    }

    @Test
    public void deeplinkTest(){
        //Android
        SelenideAppium.openAndroidDeepLink("mydemoapprn://product-details/1",
                                            "com.saucelabs.mydemoapp.rn");
        //IOS : No necesita package
        //SelenideAppium.openIOSDeepLink("mydemoapprn://product-details/1");
        Selenide.sleep(5000);
    }

    @Test
    public void scrollInHomePageWithTestNG(){
        SelenideAppium.launchApp();
        HomeScreen homeScreen = ScreenObject.screen(HomeScreen.class);
        String value = homeScreen.scroll();
        Assert.assertTrue(value.contains("Sauce Labs."));
    }

    @Test
    public void scrollInHomePageWithSelenide(){
        SelenideAppium.launchApp();
        HomeScreen homeScreen = ScreenObject.screen(HomeScreen.class);
        homeScreen.scrollAndValidate();
    }
}
