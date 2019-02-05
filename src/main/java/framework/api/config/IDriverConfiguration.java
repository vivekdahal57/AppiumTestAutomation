package framework.api.config;

/**
 * @author bibdahal
 */
public interface IDriverConfiguration {

    public long getDefaultWaitTimeout();

    public Integer getImplicitWaitTime();

    public Device getDevice();

    public void setDevice(String device);

    public String getAppPackageName();

    public String getAppActivityName();

    public boolean runOnGrid();

    public String getBaseAddress();

    public int getAppiumPort();

    public int getBootstrapPort();

    public String getDeviceUID();

    public boolean resetKeyboard();

    public boolean unicodeKeyboard();

    public enum Device {
        ANDROID("Android"),
        IPHONE("iOS"),
        FIREFOX_OS("FirefoxOS"),
        WINDOWS("Windows");

        Device(String name) {
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }
    }

}
