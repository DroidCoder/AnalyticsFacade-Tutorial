package com.thanoskarpouzis.tutorial.analyticsfacade.analytics;

import android.util.Log;

/**
 * Created by athanasioskarpouzis on 21/06/15.
 */
public class SimpleLogAnalyticsController implements AnalyticsController {
    @Override
    public String getName() {
        return "SimpleLogAnalyticsController";
    }

    @Override
    public void handleEvent(Event event) {
        if (shouldSkipEvent(event)) {
            return;
        }
        Log.v(getName(), event.toString());
    }

    @Override
    public void handleScreenChange(String screenName) {
        Log.v(getName(), "screen change: " + screenName);
    }

    @Override
    public boolean shouldSkipEvent(Event event) {
        return false;
    }
}
