package framework.api.utils;

import com.google.common.base.Function;
import framework.api.constants.TimeOuts;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

/**
 * @author bibdahal
 */
public class WaitUtils {


    public static void waitForSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ignored) {
        }

    }

    public static void waitUntil(AppiumDriver<MobileElement> driver, Integer waitTimeSeconds, Function predicate) {
        new WebDriverWait(driver, waitTimeSeconds)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class)
                .until((Function<? super WebDriver, Object>) predicate);
    }

    public static void waitUntilEnabled(AppiumDriver<MobileElement> driver, final WebElement element) {
        WaitUtils.waitUntil(driver, TimeOuts.FIVE_SECONDS, new Function<WebDriver,Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return element.isEnabled();
            }
        });
    }

    public static void waitUntilDisplayed(AppiumDriver<MobileElement> driver, final WebElement element, int timeOut) {
        WaitUtils.waitUntil(driver, timeOut, new Function<WebDriver,Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return element.isDisplayed();
            }
        });
    }

    public static void fluentWait(AppiumDriver<MobileElement> driver,By locator,int timeOuts,int pollTime){
        Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(driver)
                .withTimeout(timeOuts,TimeUnit.SECONDS)
                .pollingEvery(pollTime,TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement waitingElement = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply( WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }

    public static void waitUntilDisappear(AppiumDriver driver, final MobileElement element, int timeOut) {
        WaitUtils.waitUntil(driver, timeOut, new Function<WebDriver,Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return !element.isDisplayed();
            }
        });
    }

    public static void waitUntilVisiblityOfElement(AppiumDriver<MobileElement> driver, MobileElement element){
        WebDriverWait wait = new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
