package pages.impl;

import framework.selenium.AbstractScreenComponent;
import framework.selenium.SeleniumUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.aspectj.weaver.loadtime.IWeavingContext;
import org.openqa.selenium.support.PageFactory;
import pages.api.ISplashScreen;
import pages.api.IWelcomeScreen;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Created by bibdahal
 */
public class SplashScreen extends AbstractScreenComponent implements ISplashScreen {
    private final MobileElements mobileElements;
    private final IWelcomeScreen welcomeScreen;

    public SplashScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        mobileElements = new MobileElements(driver);
        welcomeScreen = new WelcomeScreen(driver);
    }

    public IWelcomeScreen getWelcomeScreen() {
        return welcomeScreen;
    }

    @Override
    public boolean isFullyLoaded() {
        return mobileElements.logo.isDisplayed();
    }

    @Override
    public IWelcomeScreen isPageChanged() {
        if (welcomeScreen.isButtonStartVisible()) {
            return welcomeScreen;
        } else {
            return null;
        }
    }

    private static class MobileElements {
        private MobileElements(AppiumDriver<MobileElement> driver) {
            PageFactory.initElements(driver, this);
        }

        @AndroidFindBy(accessibility = "test123")
        //@iOSXCUITFindBy(accessibility = "")
        private MobileElement logo;
    }

}
