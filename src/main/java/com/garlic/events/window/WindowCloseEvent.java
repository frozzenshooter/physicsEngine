package com.garlic.events.window;

import com.garlic.events.Event;
import com.garlic.events.EventType;
import lombok.Getter;

@Getter
public class WindowCloseEvent implements Event {
    private final EventType type = EventType.WINDOW_CLOSE;

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public String getDescription() {
        return "Window closed.";
    }
}
