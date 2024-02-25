package com.garlic.events;

import lombok.Getter;

@Getter
public enum EventType {
    WINDOW_CLOSE("window close"),
    WINDOW_RESIZE("window resize"),
    WINDOW_FOCUS("window focus"),
    WINDOW_LOST_FOCUS("window focus lost"),
    WINDOW_MOVED("window moved"),
    KEY_PRESSED("key pressed"),
    KEY_RELEASED("key released"),
    MOUSE_BUTTON_PRESSED("mouse button pressed"),
    MOUSE_BUTTON_RELEASED("mouse button released"),
    MOUSE_SCROLLED("mouse scrolled"),
    MOUSE_MOVED("mouse moved");

    private final String name;

    EventType(String name) {
        this.name = name;
    }
}