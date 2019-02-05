package pages.impl;

import framework.selenium.AbstractScreenComponent;
import framework.selenium.SeleniumUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import pages.api.ITermsAndConditionScreen;
import pages.api.IWelcomeScreen;

/**
 * Created by bibdahal
 */
public class WelcomeScreen extends AbstractScreenComponent implements IWelcomeScreen {
    private final MobileElements mobileElements;

    public WelcomeScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        mobileElements = new MobileElements(driver);
    }

    @Override
    public boolean isFullyLoaded() {
        return mobileElements.btn_Start.isDisplayed();
    }

    public boolean isLogoDisplayed(){
        try {
            return mobileElements.logo.isDisplayed();
        }catch (Exception e){
            return  false;
        }
    }

    public boolean isHeadingDisplayed(){
        try {
            return mobileElements.heading.isDisplayed();
        }catch (Exception e){
            return  false;
        }
    }

    public boolean isSubHeadingDisplayed(){
        try {
            return mobileElements.subHeading.isDisplayed();
        }catch (Exception e){
            return  false;
        }
    }
    public boolean isButtonStartVisible(){
        try {
            return mobileElements.btn_Start.isDisplayed();
        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public ITermsAndConditionScreen clickStartButton() {
        ITermsAndConditionScreen termsAndConditionScreen = PageFactory
                .initElements(getDriver(), TermsAndConditionScreen.class);
        submit();
        return termsAndConditionScreen;
    }

    /**
     * Click Submit button.
     */
    private WelcomeScreen submit() {
        SeleniumUtils.click(getDriver(), mobileElements.btn_Start);
        return this;
    }

    private static class MobileElements {
        private MobileElements(AppiumDriver<MobileElement> driver) {
            PageFactory.initElements(driver, this);
        }

        @iOSXCUITFindBy(accessibility = "WelcomeScreen_btnAccept")
        @AndroidFindBy(accessibility = "WelcomeScreen_btnAccept")
        private MobileElement btn_Start;

        @iOSXCUITFindBy(accessibility = "WelcomeScreen_lbl1")
        @AndroidFindBy(accessibility = "WelcomeScreen_lbl1")
        private  MobileElement heading;

        @iOSXCUITFindBy(accessibility = "WelcomeScreen_lbl2")
        @AndroidFindBy(accessibility = "WelcomeScreen_lbl2")
        private  MobileElement subHeading;

        @iOSXCUITFindBy(accessibility = "WelcomeScreen_img")
        @AndroidFindBy(accessibility = "WelcomeScreen_img")
        private  MobileElement logo;
    }
}
