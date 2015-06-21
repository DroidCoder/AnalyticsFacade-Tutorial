package com.thanoskarpouzis.tutorial.analyticsfacade.analytics;

import android.util.Log;

import java.util.List;

/**
 * Created by athanasioskarpouzis on 21/06/15.
 */
class OnEventRunnable implements Runnable {
    private static final String TAG = "AnalyticsFacade-evnJob";

    private final Event event;

    OnEventRunnable(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
        List<AnalyticsController> controllers = AnalyticsFacade.getControllers();

        if (controllers.size() == 0) {
            Log.w(TAG, "AnalyticsFacade has zero registered AnalyticsController. Event ignored");
        }

        for (AnalyticsController controller : controllers) {
            controller.handleEvent(event);
        }
    }
}
