package com.thanoskarpouzis.tutorial.analyticsfacade.analytics;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by athanasioskarpouzis on 21/06/15.
 */
public class Payload extends HashMap<String, String> implements Serializable {

    private static final String TAG = "Payload";

    public Payload add(String key, String value) {
        put(key, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Payload{");
        String sep = "";
        for (String key : this.keySet()) {
            stringBuilder.append(sep + key + "=" + this.get(key));
            sep = ", ";
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        for (String key : this.keySet()) {
            jsonObject.put(key, this.get(key));
        }
        return jsonObject;
    }

    String toJsoStringnOrNull() {
        String jsonString = null;
        try {
            jsonString = toJson().toString();
        } catch (Exception e) {
            Log.w(TAG, "toJsonOrNull", e);
        }
        return jsonString;
    }

    public static Payload fromJson(JSONObject jsonObject) throws JSONException {
        Payload payload = new Payload();
        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            payload.put(key, jsonObject.getString(key));
        }
        return payload;
    }

    static Payload fromJsonString(String jsonString) {
        Payload payload = new Payload();
        try {
            payload = fromJson(new JSONObject(jsonString));
        } catch (Exception e) {
            Log.w(TAG, "fromJsonString", e);
        }
        return payload;
    }
}
