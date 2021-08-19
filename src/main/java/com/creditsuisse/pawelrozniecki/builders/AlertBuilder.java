package com.creditsuisse.pawelrozniecki.builders;

import com.creditsuisse.pawelrozniecki.models.AlertEvent;

public class AlertBuilder {
    private String id;
    private Long duration;
    private String type;
    private String host;
    private boolean isAlert;

    public AlertBuilder setId(String id) {
        this.id = id;
        return this;

    }

    public AlertBuilder setDuration(Long duration) {
        this.duration = duration;
        return this;

    }

    public AlertBuilder setType(String type) {
        this.type = type;
        return this;

    }

    public AlertBuilder setHost(String host) {
        this.host = host;
        return this;

    }

    public AlertBuilder setAlert(boolean isAlert) {
        this.isAlert = isAlert;
        return this;
    }

    public AlertEvent build(){
        return new AlertEvent(id,duration,type,host,isAlert);
    }
}
