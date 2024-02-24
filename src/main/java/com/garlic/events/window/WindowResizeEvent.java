package com.garlic.events.window;

import com.garlic.events.Event;
import com.garlic.events.EventType;
import lombok.Getter;

@Getter
public class WindowResizeEvent implements Event {
    private final EventType type = EventType.WINDOW_RESIZE;

    private final int width;
    private final int height;

    public WindowResizeEvent(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public String getDescription() {
        return "Window resized to: [width:" + width + ", height" + height + "]";
    }
}
