package com.thanoskarpouzis.tutorial.analyticsfacade.analytics;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by athanasioskarpouzis on 21/06/15.
 */
public class GoogleAnalyticsController implements AnalyticsController {

    public static GoogleAnalytics analytics;
    public static Tracker tracker;


    public GoogleAnalyticsController(Application application) {
        analytics = GoogleAnalytics.getInstance(application);
        analytics.setLocalDispatchPeriod(1800);
        analytics.setDryRun(true);

        tracker = analytics.newTracker("UA-XXXXX-Y"); // Replace with actual tracker/property Id
        tracker.enableAdvertisingIdCollection(true);
    }

    @Override
    public String getName() {
        return "GoogleAnalyticsController";
    }

    @Override
    public void handleEvent(Event event) {
        if (shouldSkipEvent(event)) {
            return;
        }
        mapEvent(event);
    }

    @Override
    public void handleScreenChange(String screenName) {
        tracker.setScreenName(screenName);
    }


    @Override
    public boolean shouldSkipEvent(Event event) {
        if (event.getType().equalsIgnoreCase("internal")) {
            return true;
        }
        return false;
    }

    private void mapEvent(Event event) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory(event.getType())
                .setAction(event.getName())
                .setLabel(event.getValue());

        builder = mapPayload(builder, event.getPayload());

        tracker.send(builder.build());
    }

    private HitBuilders.EventBuilder mapPayload(HitBuilders.EventBuilder builder, Payload payload) {
        if (payload.containsKey("subscription_duration")) {
            builder.setCustomDimension(0, payload.get("subscription_duration"));
        }
        return builder;
    }

}
