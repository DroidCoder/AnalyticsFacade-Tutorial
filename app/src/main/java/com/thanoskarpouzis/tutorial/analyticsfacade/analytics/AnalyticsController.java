package com.thanoskarpouzis.tutorial.analyticsfacade.analytics;

/**
 * Created by athanasioskarpouzis on 21/06/15.
 */
public interface AnalyticsController {

    String getName();

    void handleEvent(Event event);

    void handleScreenChange(String screenName);

    boolean shouldSkipEvent(Event event);

}

