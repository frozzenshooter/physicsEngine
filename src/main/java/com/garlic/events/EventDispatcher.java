package com.garlic.events;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class EventDispatcher {

    private final EnumMap<EventType, List<EventListener>> eventListeners = new EnumMap<>(EventType.class);

    public void registerEventListener(EventType type, EventListener newListener) {

        var existingListeners = eventListeners.get(type);

        if (existingListeners == null) {
            existingListeners = new ArrayList<>();
        }

        existingListeners.add(newListener);
        eventListeners.put(type, existingListeners);
    }

    public void dispatch(Event event) {
        var listenersToCall = this.eventListeners.get(event.getType());

        if (listenersToCall != null) {
            for (var listenerToCall : listenersToCall) {
                listenerToCall.onEvent(event);
            }
        }
    }

    public void shutdown() {
        this.eventListeners.clear();
    }
}
