import framework.api.config.ExecutionContext;
import com.google.common.base.Preconditions;
import framework.api.annotations.logging.LogMethodEnter;
import framework.selenium.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import pages.config.User;
import pages.impl.TestApplication;

import static com.google.common.base.Preconditions.checkArgument;


/**
 * The Base Class for all UI Tests
 * Handles configuration management,login etc
 *
 * @author bibdahal
 */
public abstract class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected final ExecutionContext context;
    protected final Integer defaultWaitTime;
    protected DriverFactory driverFactory;
    protected AppiumDriver<MobileElement> driver;
    protected TestApplication testApplication;

    public BaseTest() {
        context = ExecutionContext.forEnvironment(System.getProperty("environment"));
        defaultWaitTime = context.getDefaultWaitTimeout();
    }

    @LogMethodEnter
    public void setUp()  {
        driverFactory = getDriverFactory();
        if (!skipDeviceObjectCreation()) {
            createNewDeviceInstance();
        }
    }

    protected final void createNewDeviceInstance() {
        Preconditions.checkState(driver == null);
        driver = driverFactory.createDriver();
        testApplication=new TestApplication(driver);
    }

    @AfterClass(alwaysRun = true)
    public final void tearDownTestClass() {
        closeBrowserInstance();
    }

    protected final void closeBrowserInstance() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


    private DriverFactory getDriverFactory(){
        if(null != driverFactory){
            return driverFactory;
        }
        return new DriverFactory(context.getDriverConfiguration());
    }

    /**
     * Override this method to return true if you want to avoid
     * creating web-driver instance automatically by BaseTest
     * Used by Tests that creates new browser instance for each
     * test
     */
    public boolean skipDeviceObjectCreation() {
        return false;
    }


    protected final User getUser() {
         return new User(context.getAppUsername(),context.getAppPassword());
    }

    protected final User getUser(String username,String password) {
        return new User(username,password);
    }

    public final AppiumDriver<MobileElement> getdriver() {
        return driver;
    }

}
