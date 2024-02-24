package com.garlic.events;

public interface Event {

    EventType getType();

    String getName();

    String getDescription();
}
