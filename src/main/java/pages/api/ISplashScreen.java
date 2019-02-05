package pages.api;

import framework.api.features.IAmScreenComponent;

/**
 * @author bibdahal
 */
public interface ISplashScreen extends IAmScreenComponent {

    /**
     * Gets Screen Element status
     *
     * @return
     */
    IWelcomeScreen isPageChanged();
}

