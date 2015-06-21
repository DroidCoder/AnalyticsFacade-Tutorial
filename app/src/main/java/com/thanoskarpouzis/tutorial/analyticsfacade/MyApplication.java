package com.thanoskarpouzis.tutorial.analyticsfacade;

import android.app.Application;
import android.content.res.Configuration;

import com.thanoskarpouzis.tutorial.analyticsfacade.analytics.AnalyticsFacade;
import com.thanoskarpouzis.tutorial.analyticsfacade.analytics.GoogleAnalyticsController;
import com.thanoskarpouzis.tutorial.analyticsfacade.analytics.SimpleLogAnalyticsController;

/**
 * Created by athanasioskarpouzis on 21/06/15.
 */
public class MyApplication extends Application {

    private AnalyticsFacade analyticsFacade;

    @Override
    public void onCreate() {
        super.onCreate();
        initAnalytics();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void initAnalytics() {
        analyticsFacade = AnalyticsFacade.init(this);
        analyticsFacade.registerController(new SimpleLogAnalyticsController());
        analyticsFacade.registerController(new GoogleAnalyticsController(this));
    }
}
