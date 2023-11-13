package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class androidTest {

    AndroidDriver driver;
    IOSDriver driver1;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setApp(System.getProperty("user.dir") + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");
        options.setPlatformName("Android");
        options.setDeviceName("esteban-test-device"); //Random name
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //Si se inicia con el plugin del wait, se puede comentar esta linea
    }

    public void setUpIOS() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 13");
        options.setApp(System.getProperty("user.dir") + "/apps/...."); //falta la aplicacion del zip

        driver1 = new IOSDriver(new URL("http://127.0.0.1:4723/"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test (groups = "android")
    public void openOption() {
        wait.until(ExpectedConditions
                        .visibilityOf(driver.findElement
                                (AppiumBy.accessibilityId("open menu")))).click();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]")).click();
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("Example");
    }

    @Test (groups = "ios")
    public void openOption2() {
        wait.until(ExpectedConditions
                .visibilityOf(driver.findElements
                        (By.name("store item text")).get(0))).click();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]")).click();
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("Example");
    }

    @Test(groups = "android")
    public void tap() throws InterruptedException {
        WebElement openMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));
        tap(openMenu);
        Thread.sleep(5000);
    }

    @Test(groups = "android")
    public void doubleTap() throws InterruptedException {
        WebElement openMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));
        doubleTap(openMenu);
        Thread.sleep(5000);
    }


    private void tap(WebElement element){
        Point point = element.getLocation();
        Dimension size = element.getSize();

        Point centerElement = getCenterOfElement(point, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1,1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    private void doubleTap(WebElement element){
        Point point = element.getLocation();
        Dimension size = element.getSize();

        Point centerElement = getCenterOfElement(point, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1,1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    private Point getCenterOfElement(Point point, Dimension size) {
        return new Point(point.getX() + size.getWidth()/2,
                point.getY()+ size.getHeight()/2);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
