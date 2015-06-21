package com.thanoskarpouzis.tutorial.analyticsfacade.analytics;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by athanasioskarpouzis on 21/06/15.
 */
public class Event implements Serializable {
    private static final long serialVersionUID = 0L;

    private final String type;
    private final String name;
    private final String value;
    private final Payload payload;
    private final Long timestamp;
    private final String screen;

    public Event(String type, String name) {
        this(type, name, null, new Payload());
    }

    public Event(String type, String name, String value) {
        this(type, name, value, new Payload());
    }

    public Event(String type, String name, Payload payload) {
        this(type, name, null, payload);
    }

    public Event(String type, String name, String value, Payload payload) {
        this(type, name, value, payload, new Date().getTime());
    }

    public Event(String type, String name, String value, Payload payload, Long timestamp) {
        this(type, name, value, payload, timestamp, AnalyticsFacade.getScreen());
    }

    Event(String type, String name, String value, Payload payload, Long timestamp, String screen) {
        this.type = type;
        this.name = name;
        this.value = value;
        this.payload = payload;
        this.timestamp = timestamp;
        this.screen = screen;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Payload getPayload() {
        return payload;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", payload=" + payload +
                ", timestamp=" + timestamp +
                ", screen=" + screen +
                '}';
    }

    public JSONObject toJson() throws JSONException {
        JSONObject eventJson = new JSONObject()
                .put("type", this.type)
                .put("name", this.name)
                .put("value", this.value)
                .put("payload", this.payload.toJson())
                .put("timestamp", this.timestamp)
                .put("screen", this.screen);

        return eventJson;
    }

    public static Event fromJson(JSONObject eventJson) throws JSONException {
        return new Event(eventJson.getString("type"),
                eventJson.getString("name"),
                eventJson.getString("value"),
                Payload.fromJson(eventJson.getJSONObject("payload")),
                eventJson.getLong("timestamp"),
                eventJson.getString("screen"));
    }

}

