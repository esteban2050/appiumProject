package screen;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.appium.AppiumCondition;
import com.codeborne.selenide.appium.AppiumSelectors;
import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.appium.conditions.CombinedAttribute;
import com.codeborne.selenide.appium.selector.CombinedBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.appium.AppiumScrollOptions.with;
import static com.codeborne.selenide.appium.ScreenObject.screen;
import static com.codeborne.selenide.appium.ScrollDirection.DOWN;


public class HomeScreen {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"store item\"]")
    @iOSXCUITFindBy(xpath = "//*[@name='store item text']")
    private WebElement product1;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"store item\"]")
    @iOSXCUITFindBy(xpath = "//*[@name='store item text']")
    private ElementsCollection products;

    private CombinedBy copyRightCombinedBy = CombinedBy
            .android(By.xpath("//android.widget.TextView[@text='© 2023 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy.']"))
            .ios(By.xpath("//XCUIElementTypeStaticText[@name='© 2023 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy.']"));

    /***
     * Explicit wait
     * When the webElement is pointing to list with the same xpath, and it is defined like a single object, it will be pointing to the first element
     * @return
     */
    public ProductDescriptionScreen clickProduct1(){
        $(product1)
                .shouldBe(visible, Duration.ofSeconds(5)) //By default: 4 sec
                .click();
        return screen(ProductDescriptionScreen.class);
    }

    public ProductDescriptionScreen clickProduct1WithList(){
        $$(products)
                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                .get(0)
                .click();
        return screen(ProductDescriptionScreen.class);
    }

    //Con Page Factory, estamos creando locator estaticos y se vuelven locators muy extensos y dificiles de mantener.
    //Por ellos se recomiendan hacer locators mediante BY
    By by1 = AppiumSelectors.byAttribute("content-desc","store item");
    By by2 = AppiumSelectors.withAttribute("content-desc","store i");
    By by3 = AppiumSelectors.byContentDescription("store item");
    By by4 = AppiumSelectors.withContentDescription("store i");
    By by5 = AppiumSelectors.byTagAndContentDescription("android.view.ViewGroup","store item");

    By by6 = AppiumSelectors.byAttribute("name","store item text");
    By by7 = AppiumSelectors.byName("store item text");

    public ProductDescriptionScreen clickProductCombined(){
        //Con los By no se puede tener un solo elemento para ambos OS, por eso se introdujo este tema del combine
        CombinedBy product = CombinedBy.android(by1).ios(by6);
        $$(product)
                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                .get(1)
                .click();
        return screen(ProductDescriptionScreen.class);
    }

    public String scroll(){
        SelenideAppium.$(copyRightCombinedBy)
                .scroll(with(DOWN,50));
        return $(copyRightCombinedBy).getText();
    }

    public String scrollAndValidate(){
        CombinedAttribute attribute = CombinedAttribute.android("text").ios("name");

        $(copyRightCombinedBy)
                .scrollTo()
                .shouldHave(AppiumCondition.attribute(attribute
                        ,"© 2023 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy."));
        return $(copyRightCombinedBy).getText();
    }
}
