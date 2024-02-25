package com.garlic.events.mouse;

import com.garlic.events.Event;
import com.garlic.events.EventType;
import lombok.Getter;

@Getter
public class MouseScrolledEvent implements Event {

    private final EventType type = EventType.MOUSE_SCROLLED;

    private final double xOffset;
    private final double yOffset;

    public MouseScrolledEvent(double xOffset, double yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public String getDescription() {
        return "Mouse scrolled (xOffset: " + xOffset + ", yOffset: " + yOffset + ").";
    }
}
