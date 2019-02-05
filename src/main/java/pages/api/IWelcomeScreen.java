package pages.api;

import framework.api.features.IAmScreenComponent;
import pages.config.User;

/**
 * @author bibdahal
 */
public interface IWelcomeScreen extends IAmScreenComponent {

    /**
     * Gets Screen Element status
     *
     * @return
     */

    public boolean isLogoDisplayed();

    public boolean isHeadingDisplayed();

    public boolean isSubHeadingDisplayed();

    public boolean isButtonStartVisible();

    ITermsAndConditionScreen clickStartButton();
}

