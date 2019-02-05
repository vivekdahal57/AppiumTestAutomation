package framework.groovy.environment

import framework.api.exceptions.NotImplementedException
import org.apache.commons.configuration.AbstractConfiguration
import org.apache.commons.configuration.Configuration

/**
 * @author bibdahal
 */
class EnvironmentConfiguration extends AbstractConfiguration implements Configuration {
    private static final String CONFIGURATION_FILE = "conf" + File.separator + "environment.groovy"
    public static final String APP_PACKAGE_NAME = "appPackageName"
    public static final String APP_ACTIVITY_NAME = "appActivityName"
    public static final String DEVICE = "device"
    public static final String DEVICE_UID = "deviceUID"
    public static final String RUN_ON_GRID = "runOnGrid"
    public static final String BASEADDRESS = "baseAddress"
    public static final String APPIUM_PORT = "appiumPort"
    public static final String BOOTSTRAP_PORT = "bootstrapPort"
    public static final String IS_RESET_KEYBOARD = "resetKeyboard"
    public static final String IS_UNICODE_KEYBOARD = "unicodeKeyboard"
    public static final String DEFAULT_WAIT_TIME_OUT = "waitTime"
    public static final String IMPLICIT_WAIT_TIME = "implicitWaitTime"
    public static final String APP_USERNAME = 'username'
    public static final String APP_PASSWORD = 'password'


    private String environment
    Map<String, String> properties


    EnvironmentConfiguration(String environment) {
        this.environment = environment

        //read in configuration from environment.groovy
        def builder = new EnvironmentBuilder()

        Binding binding = new Binding() {
            @Override
            Object getVariable(String name) {
                return { Object... args -> builder.invokeMethod(name, args) }
            }
        }


        def script = new GroovyShell(binding).parse(getConfigFile())

        script.run()

        if (environment == null) {
            properties = builder.getDefaultEnvironmentProperties()
        } else {
            properties = builder.getProperties(environment)
        }

    }

    /**
     * Two different directories are provided because the working
     * directory changes based on whether you are running via maven
     * or directly running a groovy script. Need to find a better
     * way to find resources
     * @return
     */
    File getConfigFile() {
        File configFile = new File(CONFIGURATION_FILE)
        if (configFile.exists()) return configFile

        configFile = new File("environment.groovy")
        if (configFile.exists()) return configFile

        throw new Error("Could not find environment.groovy. The current working directory is " + System.getProperty("user.dir"))
    }


    @Override
    boolean isEmpty() {
        return properties.isEmpty()
    }

    @Override
    boolean containsKey(String key) {
        return properties.containsKey(key)
    }

    @Override
    Iterator getKeys() {
        return properties.keySet().iterator()
    }

    @Override
    Object getProperty(String property) {
        return properties.get(property)
    }

    @Override
    protected void addPropertyDirect(String key, Object value) {
        throw new NotImplementedException() //not applicable
    }
}
