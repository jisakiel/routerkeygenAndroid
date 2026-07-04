package org.exobel.routerkeygen;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.millennialmedia.AppInfo;
import com.millennialmedia.MMSDK;
import com.millennialmedia.UserData;

import org.acra.ACRA;
import org.acra.config.ACRAConfiguration;
import org.acra.config.ConfigurationBuilder;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.exobel.routerkeygen.ui.NetworkActivity;
import org.exobel.routerkeygen.ui.NetworksListActivity;
import org.exobel.routerkeygen.ui.Preferences;

public class RouterKeygenApplication extends Application {

    private Tracker mTracker;

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.analytics);
            mTracker.enableAdvertisingIdCollection(false);
        }
        return mTracker;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            final ACRAConfiguration config = new ConfigurationBuilder(this)
                    .setMailTo("jisakiel@gmail.com")
                    .setCustomReportContent(new ReportField[]{
                            ReportField.APP_VERSION_NAME, ReportField.ANDROID_VERSION,
                            ReportField.PHONE_MODEL, ReportField.CUSTOM_DATA,
                            ReportField.STACK_TRACE, ReportField.LOGCAT
                    })
                    .setReportingInteractionMode(ReportingInteractionMode.TOAST)
                    .setResToastText(R.string.crash_toast_text)
                    .build();
            ACRA.init(this, config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MMSDK.initialize(this);
            AppInfo appInfo = new AppInfo();
            appInfo.setSiteId("8a8094180153530ea48c1a2d528b0066");
            MMSDK.setAppInfo(appInfo);
            UserData userData = new UserData().
                    setEthnicity(UserData.Ethnicity.HISPANIC);
            MMSDK.setUserData(userData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (BuildConfig.DEBUG) {
                StrictMode
                        .setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                                .detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .setClassInstanceLimit(
                            CancelOperationActivity.class, 2)
                    .setClassInstanceLimit(NetworksListActivity.class,
                            2)
                    .setClassInstanceLimit(NetworkActivity.class, 2)
                    .setClassInstanceLimit(Preferences.class, 2)
                    .build());

        }
    }
}