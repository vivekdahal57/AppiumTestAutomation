package framework.api.config;

import com.google.common.base.Preconditions;
import framework.api.annotations.documentation.Immutable;

/**
 * @author bibdahal
 */
@Immutable
public class DriverConfiguration implements IDriverConfiguration {
    private Device device;
    private final boolean runOnGrid;
    private final String baseAddress;
    private final int appiumPort;
    private final int bootstrapPort;
    private final String appPackageName;
    private final String appActivityName;
    private final String deviceUID;
    private final boolean resetKeyboard;
    private final boolean unicodeKeyboard;
    private final long defaultWaitTimeout;
    private final Integer implicitWaitTime;

    private DriverConfiguration(Builder builder) {
        this.device = builder.device;
        this.defaultWaitTimeout = builder.defaultWaitTime;
        this.baseAddress = builder.baseAddress;
        this.appiumPort = builder.appiumPort;
        this.bootstrapPort = builder.bootstrapPort;
        this.runOnGrid = builder.runOnGrid;
        this.implicitWaitTime = builder.implicitWaitTime;
        this.appActivityName = builder.appActivityName;
        this.appPackageName = builder.appPackageName;
        this.deviceUID = builder.deviceUID;
        this.resetKeyboard = builder.resetKeyboard;
        this.unicodeKeyboard = builder.unicodeKeyboard;
    }

    public static Builder newBuilder(String device, Long defaultWaitTimeout) {
        return new Builder(device, defaultWaitTimeout);
    }

    @Override
    public Device getDevice() {
        return device;
    }

    @Override
    public long getDefaultWaitTimeout() {
        return defaultWaitTimeout;
    }

    @Override
    public Integer getImplicitWaitTime() {
        return implicitWaitTime;
    }

    @Override
    public boolean runOnGrid() {
        return runOnGrid;
    }

    @Override
    public String getBaseAddress() {
        return baseAddress;
    }

    @Override
    public int getAppiumPort() {
        return appiumPort;
    }

    @Override
    public int getBootstrapPort() {
        return bootstrapPort;
    }

    @Override
    public String getAppPackageName() {
        return appPackageName;
    }

    @Override
    public String getAppActivityName() {
        return appActivityName;
    }

    @Override
    public String getDeviceUID() {
        return deviceUID;
    }

    @Override
    public boolean resetKeyboard() {
        return resetKeyboard;
    }

    @Override
    public boolean unicodeKeyboard() {
        return unicodeKeyboard;
    }

    @Override
    public void setDevice(String device) {
        if (!device.isEmpty()) {
            this.device = Device.valueOf(device);
        }
    }

    @Override
    public String toString() {
        return "DriverConfiguration{" +
                "device='" + device + '\'' +
                ", runOnGrid=" + runOnGrid +
                ", hubUrl='" + baseAddress + '\'' +
                ", appiumPort='" + appiumPort + '\'' +
                ", bootstrapPort='" + bootstrapPort + '\'' +
                ", defaultWaitTimeout=" + defaultWaitTimeout +
                ", appAPackageName=" + appPackageName +
                ", appActivityName=" + appActivityName +
                '}';
    }


    public static class Builder {
        //RequiredParameters
        private final Device device;
        private final Long defaultWaitTime;

        //Optional parameters
        private String baseAddress = null;
        private int appiumPort = 0;
        private int bootstrapPort = 0;
        private Boolean runOnGrid = false;
        private Integer implicitWaitTime = 0;
        private String appActivityName = null;
        private String appPackageName = null;
        private String deviceUID = null;
        private Boolean resetKeyboard = false;
        private Boolean unicodeKeyboard = false;

        private Builder(String device, Long defaultWaitTime) {
            Preconditions.checkNotNull(device != null);
            Preconditions.checkNotNull(defaultWaitTime);

            this.device = Device.valueOf(device);
            this.defaultWaitTime = defaultWaitTime;
        }

        public Builder runOnGrid(boolean runOnGrid) {
            this.runOnGrid = runOnGrid;
            return this;
        }

        public Builder setBaseAddress(String baseAddress) {
            Preconditions.checkNotNull(baseAddress);
            this.baseAddress = baseAddress;
            return this;
        }

        public Builder setImplicitWaitTime(Integer implicitWaitTime) {
            this.implicitWaitTime = implicitWaitTime;
            return this;
        }

        public Builder setAppPackageName(String appPackageName) {
            this.appPackageName = appPackageName;
            return this;
        }

        public Builder setAppActivityName(String appActivityName) {
            this.appActivityName = appActivityName;
            return this;
        }

        public Builder setDeviceUID(String deviceUID) {
            this.deviceUID = deviceUID;
            return this;
        }

        public Builder setResetKeyboard(boolean resetKeyboard) {
            this.resetKeyboard = resetKeyboard;
            return this;
        }

        public Builder setUnicodeKeyboard(boolean unicodeKeyboard) {
            this.unicodeKeyboard = unicodeKeyboard;
            return this;
        }

        public DriverConfiguration build() {
            if (runOnGrid && baseAddress == null)
                if (appiumPort == 0)
                    throw new IllegalStateException("HubUrl is Null");
            return new DriverConfiguration(this);
        }

        public Builder setAppiumPort(int appiumPort) {
            this.appiumPort = appiumPort;
            return this;
        }

        public Builder setBootstrapPort(int bootstrapPort) {
            this.bootstrapPort = bootstrapPort;
            return this;
        }
    }


}
