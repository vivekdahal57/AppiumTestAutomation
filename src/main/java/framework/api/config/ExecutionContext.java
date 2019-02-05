package framework.api.config;

import framework.api.dto.Application;
import framework.groovy.environment.EnvironmentConfiguration;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.SystemConfiguration;

import static framework.groovy.environment.EnvironmentConfiguration.*;

/**
 * @author bibdahal
 */
public class ExecutionContext {
    private final Application application;
    private final Integer waitTime;
    private final Configuration configuration;
    private final DriverConfiguration driverConfiguration;

    public static ExecutionContext forEnvironment(String environment) {
        return new ExecutionContext(environment);
    }

    private ExecutionContext(String environment) {
        CompositeConfiguration compositeConfiguration = new CompositeConfiguration();
        compositeConfiguration.addConfiguration(new SystemConfiguration());
        compositeConfiguration
                .addConfiguration(new EnvironmentConfiguration(environment));
        compositeConfiguration.setThrowExceptionOnMissing(true);

        this.configuration = compositeConfiguration;

        application = new Application(configuration.getString(APP_PACKAGE_NAME), configuration.getString(APP_ACTIVITY_NAME));
        waitTime = configuration.getInt(DEFAULT_WAIT_TIME_OUT);
        driverConfiguration = buildDriverConfiguration();
    }

    private DriverConfiguration buildDriverConfiguration() {
        DriverConfiguration.Builder builder = DriverConfiguration
                .newBuilder(configuration.getString(DEVICE),
                        configuration.getLong(DEFAULT_WAIT_TIME_OUT));

        if (configuration.containsKey(RUN_ON_GRID))
            builder.runOnGrid(configuration.getBoolean(RUN_ON_GRID));

        if (configuration.containsKey(BASEADDRESS))
            builder.setBaseAddress(configuration.getString(BASEADDRESS));

        if (configuration.containsKey(APPIUM_PORT))
            builder.setAppiumPort(configuration.getInt(APPIUM_PORT));

        if (configuration.containsKey(BOOTSTRAP_PORT))
            builder.setBootstrapPort(configuration.getInt(BOOTSTRAP_PORT));

        if (configuration.containsKey(IMPLICIT_WAIT_TIME))
            builder.setImplicitWaitTime(
                    configuration.getInt(IMPLICIT_WAIT_TIME));

        if (configuration.containsKey(APP_PACKAGE_NAME))
            builder.setAppPackageName(
                    configuration.getString(APP_PACKAGE_NAME));

        if (configuration.containsKey(APP_ACTIVITY_NAME))
            builder.setAppActivityName(
                    configuration.getString(APP_ACTIVITY_NAME));

        if (configuration.containsKey(DEVICE_UID))
            builder.setDeviceUID(
                    configuration.getString(DEVICE_UID));

        if (configuration.containsKey(IS_RESET_KEYBOARD))
            builder.setResetKeyboard(
                    configuration.getBoolean(IS_RESET_KEYBOARD));

        if (configuration.containsKey(IS_UNICODE_KEYBOARD))
            builder.setUnicodeKeyboard(
                    configuration.getBoolean(IS_UNICODE_KEYBOARD));

        return builder.build();
    }

    public Application getApplication() {
        return application;
    }

    public Integer getDefaultWaitTimeout() {
        return waitTime;
    }

    public IDriverConfiguration getDriverConfiguration() {
        return driverConfiguration;
    }

    public String getAppUsername() {
        return configuration.getString(APP_USERNAME);
    }

    public String getAppPassword() {
        return configuration.getString(APP_PASSWORD);
    }
}
