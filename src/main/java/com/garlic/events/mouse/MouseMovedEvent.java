package com.garlic.events.mouse;

import com.garlic.events.Event;
import com.garlic.events.EventType;
import lombok.Getter;

@Getter
public class MouseMovedEvent implements Event {

    private final EventType type = EventType.MOUSE_MOVED;

    private final double xPos;
    private final double yPos;

    public MouseMovedEvent(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public String getDescription() {
        return "Mouse moved (xPos: " + xPos + ", yPos: " + yPos + ").";
    }
}
