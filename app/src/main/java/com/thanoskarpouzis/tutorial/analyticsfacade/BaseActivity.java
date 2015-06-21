package com.thanoskarpouzis.tutorial.analyticsfacade;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;

import com.thanoskarpouzis.tutorial.analyticsfacade.analytics.AnalyticsFacade;
import com.thanoskarpouzis.tutorial.analyticsfacade.analytics.Event;

/**
 * Created by athanasioskarpouzis on 21/06/15.
 */
public abstract class BaseActivity extends ActionBarActivity {

    @Override
    protected void onResume() {
        super.onResume();
        AnalyticsFacade.setScreen(getName());
        AnalyticsFacade.collect(new Event("app navigation", "activity resume", getName()));
    }

    public abstract String getName();
}
