package com.garlic.events;

@FunctionalInterface
public interface EventCallback {
    void onEvent(Event event);
}
