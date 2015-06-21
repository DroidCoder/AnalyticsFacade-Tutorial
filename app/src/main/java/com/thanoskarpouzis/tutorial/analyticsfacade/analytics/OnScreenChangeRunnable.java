package com.thanoskarpouzis.tutorial.analyticsfacade.analytics;

import android.util.Log;

import java.util.List;

/**
 * Created by athanasioskarpouzis on 21/06/15.
 */
class OnScreenChangeRunnable implements Runnable {
    private static final String TAG = "AnalyticsFacade-scrJob";

    private final String screenName;

    OnScreenChangeRunnable(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public void run() {
        List<AnalyticsController> controllers = AnalyticsFacade.getControllers();

        if (controllers.size() == 0) {
            Log.w(TAG, "AnalyticsFacade has zero registered AnalyticsController. Screen change ignored");
        }

        for (AnalyticsController controller : controllers) {
            controller.handleScreenChange(screenName);
        }
    }
}
