
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.api.ISplashScreen;
import pages.api.ITermsAndConditionScreen;
import pages.api.IWelcomeScreen;

/**
 * Created by i82325 on 1/24/2019.
 */
public class TestFile extends BaseTest {
    IWelcomeScreen welcomeScreen;
    ISplashScreen splashScreen;
    ITermsAndConditionScreen termsAndConditionScreen;

    @BeforeClass
    public void setUp() {
        super.setUp();
        welcomeScreen = testApplication.open(context.getApplication());
        welcomeScreen.doWaitTillFullyLoaded(context.getDefaultWaitTimeout());
    }

    @Test
    public void testMethod() {
        welcomeScreen.isButtonStartVisible();
    }
}
