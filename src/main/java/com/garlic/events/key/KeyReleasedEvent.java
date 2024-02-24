package com.garlic.events.key;

import com.garlic.events.Event;
import com.garlic.events.EventType;
import lombok.Getter;

@Getter
public class KeyReleasedEvent implements Event {
    private final EventType type = EventType.KEY_RELEASED;

    private final KeyCode keyCode;

    public KeyReleasedEvent(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public String getDescription() {
        return "Key (" + keyCode.getCode() + " - " + keyCode.name().toLowerCase() + ") pressed.";
    }
}
