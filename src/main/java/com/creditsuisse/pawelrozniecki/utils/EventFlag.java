package com.creditsuisse.pawelrozniecki.utils;

import com.creditsuisse.pawelrozniecki.models.AlertEvent;
import com.creditsuisse.pawelrozniecki.models.Event;
import com.creditsuisse.pawelrozniecki.builders.AlertBuilder;

public class EventFlag {
    boolean isAlert;

    public AlertEvent createFlagEvent(Event startedEvent, Event finishedEvent) {
            Long duration = finishedEvent.getTimestamp() - startedEvent.getTimestamp();
            isAlert = duration > 4;
            return  new AlertBuilder()
                    .setId(startedEvent.getId())
                    .setDuration(duration)
                    .setHost(startedEvent.getHost())
                    .setType(startedEvent.getType())
                    .setAlert(isAlert)
                    .build();

    }
}
