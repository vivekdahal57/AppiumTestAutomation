package framework.api.dto;

/**
 * @author bibdahal
 */
public class Application {
    private final String appPackageName;
    private final String appActivityName;

    public Application(String appPackageName, String appActivityName) {
        this.appPackageName = appPackageName;
        this.appActivityName = appActivityName;
    }

    public String getAppPackageName() {
        return appPackageName;
    }

    public String getAppActivityName() {
        return appActivityName;
    }

    @Override
    public String toString() {
        return "Application{appPackageName='" + appPackageName + '\'' + ", appActivityName='" + appActivityName + '\'' + '}';
    }
}
