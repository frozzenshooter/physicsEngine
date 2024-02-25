package com.garlic.events.mouse;

import com.garlic.events.Event;
import com.garlic.events.EventType;
import lombok.Getter;

@Getter
public class MouseButtonPressedEvent implements Event {
    private final EventType type = EventType.MOUSE_BUTTON_PRESSED;

    private final MouseCode mouseCode;

    public MouseButtonPressedEvent(MouseCode mouseCode) {
        this.mouseCode = mouseCode;
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public String getDescription() {
        return "Mouse (" + mouseCode.getCode() + " - " + mouseCode.name().toLowerCase() + ") pressed.";
    }
}
