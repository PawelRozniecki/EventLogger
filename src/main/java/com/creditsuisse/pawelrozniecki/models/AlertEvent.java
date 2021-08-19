package com.creditsuisse.pawelrozniecki.models;

public class AlertEvent{

    private final String id;
    private Long duration;
    private String type;
    private String host;
    private boolean isAlert;


    public AlertEvent(String id, Long duration, String type, String host, boolean isAlert) {
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.host = host;
        this.isAlert = isAlert;
    }

    public String getId() {
        return id;
    }

    public Long getDuration() {
        return duration;
    }

    public String getType() {
        return type;
    }

    public String getHost() {
        return host;
    }

    public boolean isAlert() {
        return isAlert;
    }

    @Override
    public String toString() {
        return "AlertEvent{" +
                "id='" + id + '\'' +
                ", duration=" + duration +
                ", type=" + type +
                ", host=" + host +
                ", isAlert=" + isAlert +
                '}';
    }
}
