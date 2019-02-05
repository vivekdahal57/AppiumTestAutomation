environments(default: 'androidLocal') {
    //the default environment to be used. Can be overridden with System property environment eg mvn test -Denvironment='scrumqc'

    /**
     * Default Values for all environment
     * Can be overridden for each environment individually
     * Also all values can be overidden using system property, eg mvn test -Denvironment='androidLocal' -DrunOnGrid=false
     */
    defaultValues {
        runOnGrid 'false'
        baseAddress '127.0.0.1'
        appiumPort '4729'
        bootstrapPort '4730'
        device 'ANDROID'
        waitTime '220'
        resetKeyboard false
        unicodeKeyboard true
    }

    //Local Environment to be used for development
    environment(name: 'androidLocal') {
        appPackageName 'eu.niko.smart.naswi.personalization'
        appActivityName 'md583f23632ab4e1cbe729947afcc3e1b22.SplashActivity'
        runOnGrid false
        device 'ANDROID'
        deviceUID 'ffc4d2d70504'
        implicitWaitTime '300'
        resetKeyboard false
        unicodeKeyboard true
        //User and password
        username 'tattniko+3488@gmail.com'
        password 'Test123.'
    }

    environment(name: 'ioslocal') {
        appPackageName 'eu.niko.smart.naswi.personalization'
        appActivityName 'md583f23632ab4e1cbe729947afcc3e1b22.MainActivity'
        runOnGrid false
        device 'IPHONE'
        deviceUID 'ffc4d2d70504'
        implicitWaitTime '300'
        resetKeyboard true
        unicodeKeyboard true
        //User and password
        username 'tattniko+3488@gmail.com'
        password 'Test123.'
    }
}