package pages.impl;

import framework.api.dto.Application;
import framework.selenium.AbstractScreenComponent;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.PageFactory;
import pages.api.ISplashScreen;
import pages.api.IWelcomeScreen;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * @author bibdahal
 */
public class TestApplication extends AbstractScreenComponent {

    public TestApplication(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public IWelcomeScreen open(Application application) {
        String activityName = application.getAppActivityName();
        String packageName = application.getAppPackageName();
        checkArgument(!isNullOrEmpty(activityName));
        checkArgument(!isNullOrEmpty(packageName));
        logger.info("Opening {}", activityName, packageName);
        return PageFactory.initElements(getDriver(), WelcomeScreen.class);
    }

    @Override
    public boolean isFullyLoaded() {
        return true;
    }

    public void quit() {
        getDriver().quit();
    }
}
