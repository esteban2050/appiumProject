package screen;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.appium.ScrollDirection.DOWN;

public class ProductDescriptionScreen {

    @AndroidFindBy(accessibility = "Add To Cart button")
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Add To Cart'")
    private WebElement addToCart;

    public void checkWhetherAddToCartButtonIsPresent(){
        $(addToCart)
                .scrollTo()
                .shouldHave(visible);
    }
}
