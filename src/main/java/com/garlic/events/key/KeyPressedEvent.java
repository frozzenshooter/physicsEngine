package com.garlic.events.key;

import com.garlic.events.Event;
import com.garlic.events.EventType;
import lombok.Getter;

@Getter
public class KeyPressedEvent implements Event {
    private final EventType type = EventType.KEY_PRESSED;

    private final KeyCode keyCode;
    private final boolean repeated;

    public KeyPressedEvent(KeyCode keyCode, boolean repeated) {
        this.keyCode = keyCode;
        this.repeated = repeated;
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public String getDescription() {
        var repeatedString = repeated ? "repeated" : "not repeated";
        return "Key (" + keyCode.getCode() + " - " + keyCode.name().toLowerCase() + ") pressed (" + repeatedString + ").";
    }
}
