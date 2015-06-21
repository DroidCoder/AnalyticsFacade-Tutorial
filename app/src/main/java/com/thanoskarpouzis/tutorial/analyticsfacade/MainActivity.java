package com.thanoskarpouzis.tutorial.analyticsfacade;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.thanoskarpouzis.tutorial.analyticsfacade.analytics.AnalyticsFacade;
import com.thanoskarpouzis.tutorial.analyticsfacade.analytics.Event;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.dummy_button).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getName() {
        return "MainActivity";
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dummy_button) {
            AnalyticsFacade.collect(new Event("button click", "dummy button"));
        }
    }
}
