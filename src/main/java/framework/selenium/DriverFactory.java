package framework.selenium;

import framework.api.config.AppiumServer;
import framework.api.config.IDriverConfiguration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author bibdahal
 */
public class DriverFactory {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IDriverConfiguration driverConfiguration;
    private static final String APPLICATION_FILE = "conf" + File.separator + "android" + File.separator + "personalization.apk";

    public DriverFactory(IDriverConfiguration config) {
        checkArgument(config != null);
        driverConfiguration = config;
    }

    public final AppiumDriver createDriver() {
        startAppiumServerServer();
        AppiumDriver<MobileElement> driver = newDriver();
//        driver.manage().timeouts().pageLoadTimeout(driverConfiguration.getDefaultWaitTimeout(), TimeUnit.SECONDS);
        if (driverConfiguration.getImplicitWaitTime() > 0) {
            driver.manage().timeouts().implicitlyWait(driverConfiguration.getImplicitWaitTime(), TimeUnit.SECONDS);
        }
        //ensure that app-driver is only accessed from the thread on which it was constructed
        //commented out since it creates a proxy and we cannot get browser name and other capabilities from the proxy
        //return ThreadGuard.protect(webDriver);
        return driver;
    }

    private AppiumDriver<MobileElement> newDriver() {
        return newDriver(false);
    }

    private AppiumDriver<MobileElement> newDriver(boolean noReset) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (driverConfiguration.runOnGrid()) {
            //todo support remote driver
            return null;
        } else {
            switch (driverConfiguration.getDevice()) {
                case ANDROID:
                    File appFile = new File(APPLICATION_FILE);
                    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, driverConfiguration.getDevice());
                    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                    capabilities.setCapability(MobileCapabilityType.NO_RESET, noReset);
                    capabilities.setCapability(MobileCapabilityType.UDID, driverConfiguration.getDeviceUID());
                    capabilities.setCapability("appPackage", driverConfiguration.getAppPackageName());
                    capabilities.setCapability(MobileCapabilityType.APP, appFile.getAbsolutePath());
                    capabilities.setCapability("appActivity", driverConfiguration.getAppActivityName());
//                    capabilities.setCapability("unicodeKeyboard", driverConfiguration.unicodeKeyboard());
//                    capabilities.setCapability("resetKeyboard", driverConfiguration.resetKeyboard());
                    //capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
                    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
                    return new AndroidDriver<>(this.getHubUrl(), capabilities);
                case IPHONE:
                    //todo test for ios
                    capabilities.setCapability(MobileCapabilityType.APP, new File("app's bundle id"));
                    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "ios device");
                    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                    capabilities.setCapability(MobileCapabilityType.NO_RESET, noReset);
                    capabilities.setCapability(MobileCapabilityType.UDID, driverConfiguration.getDeviceUID());
                    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
                    return new IOSDriver<>(this.getHubUrl(), capabilities);
                default:
                    throw new RuntimeException("Unknown platform " + driverConfiguration.getDevice());
            }
        }
    }

    private URL getHubUrl() {
        String url = "http://" + driverConfiguration.getBaseAddress() + ":" + driverConfiguration.getAppiumPort() + "/wd/hub";
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed hubUrl " + url);
        }
    }

    public void startAppiumServerServer() {
        AppiumServer appiumServer = new AppiumServer();
        String host = driverConfiguration.getBaseAddress();
        int persBootstrapPort = driverConfiguration.getBootstrapPort();
        int persPort = driverConfiguration.getAppiumPort();

        if (!appiumServer.checkIfServerIsRunning(persPort)) {
            logger.info("Starting Personalization appium server...");
            appiumServer.start(host, persPort, persBootstrapPort);
        } else {
            logger.info("Appium Server already running on Port - " + persPort);
        }
        while (!appiumServer.checkIfServerIsRunning(persPort)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
