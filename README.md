
1. start with a fresh android studio project
2. register project with firebase
3. create firebase access `google-services.json`
4. add .json file to `/app` directory
5. add dependency on gms plugin in base build.gradle:

```
buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.0-rc02'
        classpath 'com.google.gms:google-services:4.2.0'
    ...
```

6. apply plugin by adding to app/build.gradle:
 `apply plugin: 'com.google.gms.google-services'`

7. add Crashlytics by following [instructions](https://firebase.google.com/docs/crashlytics/get-started?platform=android)
8. build app and see that it installs and shows empty activity `./gradlew app:installDebug`

9. Use AndroidStudio's apk analyzer to analyze app's apk. Then look at full manifest file:

<details><summary>Click to show</summary
	```
<?xml version="1.0" encoding="utf-8"?>
<manifest
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:versionCode="1"
    android:versionName="1.0"
    android:compileSdkVersion="29"
    android:compileSdkVersionCodename="10"
    package="com.example.myapplication"
    platformBuildVersionCode="29"
    platformBuildVersionName="10">

    <uses-sdk
        android:minSdkVersion="22"
        android:targetSdkVersion="29" />

    <uses-permission
        android:name="android.permission.INTERNET" />

    <uses-permission
        android:name="android.permission.ACCESS_NETWORK_STATE" />

    <uses-permission
        android:name="android.permission.WAKE_LOCK" />

    <uses-permission
        android:name="com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE" />

    <uses-permission
        android:name="com.google.android.c2dm.permission.RECEIVE" />

    <application
        android:theme="@ref/0x7f0c0005"
        android:label="@ref/0x7f0b0027"
        android:icon="@ref/0x7f0a0000"
        android:debuggable="true"
        android:allowBackup="true"
        android:supportsRtl="true"
        android:roundIcon="@ref/0x7f0a0001"
        android:appComponentFactory="androidx.core.app.CoreComponentFactory">

        <activity
            android:name="com.example.myapplication.MainActivity">

            <intent-filter>

                <action
                    android:name="android.intent.action.MAIN" />

                <category
                    android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <provider
            android:name="com.crashlytics.android.CrashlyticsInitProvider"
            android:exported="false"
            android:authorities="com.example.myapplication.crashlyticsinitprovider"
            android:initOrder="90" />

        <receiver
            android:name="com.google.android.gms.measurement.AppMeasurementReceiver"
            android:enabled="true"
            android:exported="false" />

        <receiver
            android:name="com.google.android.gms.measurement.AppMeasurementInstallReferrerReceiver"
            android:permission="android.permission.INSTALL_PACKAGES"
            android:enabled="true"
            android:exported="true">

            <intent-filter>

                <action
                    android:name="com.android.vending.INSTALL_REFERRER" />
            </intent-filter>
        </receiver>

        <service
            android:name="com.google.android.gms.measurement.AppMeasurementService"
            android:enabled="true"
            android:exported="false" />

        <service
            android:name="com.google.android.gms.measurement.AppMeasurementJobService"
            android:permission="android.permission.BIND_JOB_SERVICE"
            android:enabled="true"
            android:exported="false" />

        <service
            android:name="com.google.firebase.components.ComponentDiscoveryService"
            android:exported="false">

            <meta-data
                android:name="com.google.firebase.components:com.google.firebase.analytics.connector.internal.AnalyticsConnectorRegistrar"
                android:value="com.google.firebase.components.ComponentRegistrar" />

            <meta-data
                android:name="com.google.firebase.components:com.google.firebase.iid.Registrar"
                android:value="com.google.firebase.components.ComponentRegistrar" />
        </service>

        <receiver
            android:name="com.google.firebase.iid.FirebaseInstanceIdReceiver"
            android:permission="com.google.android.c2dm.permission.SEND"
            android:exported="true">

            <intent-filter>

                <action
                    android:name="com.google.android.c2dm.intent.RECEIVE" />
            </intent-filter>
        </receiver>

        <provider
            android:name="com.google.firebase.provider.FirebaseInitProvider"
            android:exported="false"
            android:authorities="com.example.myapplication.firebaseinitprovider"
            android:initOrder="100" />

        <activity
            android:theme="@ref/0x01030010"
            android:name="com.google.android.gms.common.api.GoogleApiActivity"
            android:exported="false" />

        <meta-data
            android:name="com.google.android.gms.version"
            android:value="@ref/0x7f080004" />
    </application>
</manifest>
```
</details>


### Congratulations, you have a bare minimum app running crashlytics.


8. run the supplied instrumentation test (generated when creating new project `./gradlew app:connectedDebugAndroidTest`
9. see test pass
10. use Android Studio's APK analyzer to look at the generated ***app-debug-androidTest.apk***. Then look at it's Manifest:

<details> <summary>click to expand</summary>

```
<?xml version="1.0" encoding="utf-8"?>
<manifest
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:compileSdkVersion="29"
    android:compileSdkVersionCodename="10"
    package="com.example.myapplication.test"
    platformBuildVersionCode="29"
    platformBuildVersionName="10">

    <uses-sdk
        android:minSdkVersion="22"
        android:targetSdkVersion="29" />

    <instrumentation
        android:label="Tests for com.example.myapplication"
        android:name="androidx.test.runner.AndroidJUnitRunner"
        android:targetPackage="com.example.myapplication"
        android:handleProfiling="false"
        android:functionalTest="false" />

    <uses-permission
        android:name="android.permission.REORDER_TASKS" />

    <application
        android:debuggable="true">

        <uses-library
            android:name="android.test.runner" />

        <activity
            android:theme="@ref/0x01030005"
            android:name="androidx.test.core.app.InstrumentationActivityInvoker$BootstrapActivity">

            <intent-filter>

                <action
                    android:name="android.intent.action.MAIN" />
            </intent-filter>
        </activity>

        <activity
            android:theme="@ref/0x01030005"
            android:name="androidx.test.core.app.InstrumentationActivityInvoker$EmptyActivity">

            <intent-filter>

                <action
                    android:name="android.intent.action.MAIN" />
            </intent-filter>
        </activity>

        <activity
            android:theme="@ref/0x0103000b"
            android:name="androidx.test.core.app.InstrumentationActivityInvoker$EmptyFloatingActivity">

            <intent-filter>

                <action
                    android:name="android.intent.action.MAIN" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```
</details>

***The important thing to see is that the test apk DOES NOT contain any of the firebase/GMS code added to the app apk by the GMS plugin.***

### Now things get fun, we are going to create a test-only module:
1. Back in android studio's project Structure create a new "android library" module. call it `mylibrary`
2. change the plugin type in `mylibrary/build.gradle` and link to `app` module by adding these lines in the correct block:

```
apply plugin: 'com.android.library' // REMOVE THIS
apply plugin: 'com.android.test' // ADD THIS

android {
    defaultConfig {
        // add this so we can reference app:
        testApplicationId 'com.example.myapplication.test' // ADD THIS
        ...
    }

    // Set the target app project. The module specified here should contain the production code test should run against.
    targetProjectPath ':app' // ADD THIS

}

dependencies {
   implementation project(':app') // ADD THIS
	implementation 'androidx.appcompat:appcompat:1.0.2'
	// SEE HERE: these test dependenceies are now added via implementation, not androidTestImplementation
	implementation 'androidx.test.ext:junit:1.1.1'
   implementation 'androidx.test:runner:1.2.0'
   implementation 'androidx.test.espresso:espresso-core:3.2.0'
}
```
3. Remove the generated instrumentation test in the `androidTest` directory. We do this just to be sure we know which tests are running when we run tests in a few steps:

```
$ rm mylibrary/src/androidTest/java/com/example/mylibrary/ExampleInstrumentedTest.java
```

4. create a new test *in the main directory of the test module that is identical to the instrumentation test in the app module*:

```
$ cp app/src/androidTest/java/com/example/myapplication/ExampleInstrumentedTest.java mylibrary/src/main/java/com/example/mylibrary/ExampleInstrumentedTest.java
```

5. Open newly created test and fix package

```
package com.example.myapplication; // remove this
package com.example.mylibrary; // add this
```

4. now you are ready to run the instrumentation test in your new test-only module:
`$ ./gradlew clean mylibrary:connectedDebugAndroidTest`

5. again using AndroidStudio's apk analyzer look at test apk geneated above. then look at manifest:

<details><summary>click to expand</summary>

```
<?xml version="1.0" encoding="utf-8"?>
<manifest
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:compileSdkVersion="29"
    android:compileSdkVersionCodename="10"
    package="com.example.myapplication.test"
    platformBuildVersionCode="29"
    platformBuildVersionName="10">

    <uses-sdk
        android:minSdkVersion="22"
        android:targetSdkVersion="29" />

    <instrumentation
        android:label="Tests for com.example.myapplication"
        android:name="androidx.test.runner.AndroidJUnitRunner"
        android:targetPackage="com.example.myapplication"
        android:handleProfiling="false"
        android:functionalTest="false" />

    <uses-permission
        android:name="android.permission.REORDER_TASKS" />

    <application
        android:debuggable="true"
        android:appComponentFactory="androidx.core.app.CoreComponentFactory">

        <uses-library
            android:name="android.test.runner" />

        <activity
            android:theme="@ref/0x01030005"
            android:name="androidx.test.core.app.InstrumentationActivityInvoker$BootstrapActivity">

            <intent-filter>

                <action
                    android:name="android.intent.action.MAIN" />
            </intent-filter>
        </activity>

        <activity
            android:theme="@ref/0x01030005"
            android:name="androidx.test.core.app.InstrumentationActivityInvoker$EmptyActivity">

            <intent-filter>

                <action
                    android:name="android.intent.action.MAIN" />
            </intent-filter>
        </activity>

        <activity
            android:theme="@ref/0x0103000b"
            android:name="androidx.test.core.app.InstrumentationActivityInvoker$EmptyFloatingActivity">

            <intent-filter>

                <action
                    android:name="android.intent.action.MAIN" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```
</details>

