package com.creditsuisse.pawelrozniecki.models;

import java.util.Optional;

import com.creditsuisse.pawelrozniecki.enums.State;
import org.json.simple.JSONObject;

public class Event {
    public static final String JSON_ID = "id";
    public static final String JSON_STATE = "state";
    public static final String JSON_TIMESTAMP = "timestamp";
    public static final String JSON_TYPE = "type";
    public static final String JSON_HOST = "host";


    private String id;
    private State state;
    private Long timestamp;
    private String type;
    private String host;


    public Event(JSONObject jsonObject){
        this.id = (String) jsonObject.get(JSON_ID);
        this.state = State.valueOf((String) jsonObject.get(JSON_STATE));
        this.timestamp = (long) jsonObject.get(JSON_TIMESTAMP);
        this.host = (String) jsonObject.get(JSON_HOST);
        this.type = (String) jsonObject.get(JSON_TYPE);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", state=" + state +
                ", timestamp=" + timestamp +
                ", type=" + type +
                ", host=" + host +
                '}';
    }

    public String getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public String getHost() {
        return host;
    }
}
