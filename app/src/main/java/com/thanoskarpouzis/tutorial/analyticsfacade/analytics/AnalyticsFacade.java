package com.thanoskarpouzis.tutorial.analyticsfacade.analytics;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by athanasioskarpouzis on 21/06/15.
 */
public class AnalyticsFacade {

    private static final String TAG = "AnalyticsFacade";

    private static AnalyticsFacade instance;

    private final ArrayList<AnalyticsController> controllers;
    private final ExecutorService executor;
    private String currentScreen = "";

    private AnalyticsFacade(Application application) {
        this.controllers = new ArrayList<>();
        this.executor = Executors.newSingleThreadExecutor();
        Log.v(TAG, "Analytics Facade for " + application.getPackageName());
    }

    public static AnalyticsFacade init(Application application) {
        instance = new AnalyticsFacade(application);
        return instance;
    }

    public static void setScreen(String screenName) {
        if (instance == null) {
            Log.w(TAG, "AnalyticsFacade has not been initialized. Screen updated ignored");
            return;
        }
        instance.currentScreen = screenName;
        instance.executor.execute(new OnScreenChangeRunnable(screenName));
    }

    public static void collect(Event event) {
        if (instance == null) {
            Log.w(TAG, "AnalyticsFacade has not been initialized. Event ignored");
            return;
        }
        instance.executor.execute(new OnEventRunnable(event));
    }

    public static void registerController(AnalyticsController controller) {
        if (instance == null) {
            Log.w(TAG, "AnalyticsFacade has not been initialized. Controller ignored");
            return;
        }
        instance.controllers.add(controller);
    }

    public static void unregisterController(AnalyticsController controller) {
        if (instance == null) {
            return;
        }
        instance.controllers.remove(controller);
    }

    public static void unregisterController(String controllerName) {
        if (instance == null) {
            return;
        }
        instance.controllers.removeAll(instance.findControllersByName(controllerName));
    }

    static List<AnalyticsController> getControllers() {
        if (instance == null) {
            return new ArrayList<>(0);
        } else {
            return instance.controllers;
        }
    }

    static String getScreen() {
        if (instance == null) {
            return "";
        }
        return instance.currentScreen;
    }

    private List<AnalyticsController> findControllersByName(String controllerName) {
        List<AnalyticsController> matchingControllers = new ArrayList<>();
        for (AnalyticsController controller : instance.controllers) {
            if (controller.getName().equalsIgnoreCase(controllerName)) {
                matchingControllers.add(controller);
            }
        }
        return matchingControllers;
    }

}
