package com.garlic.events.mouse;

import lombok.Getter;

@Getter
public enum MouseCode {

    BUTTON_LEFT(0),
    BUTTON_RIGHT(1),
    BUTTON_MIDDLE(2),
    BUTTON3(3),
    BUTTON4(4),
    BUTTON5(5),
    BUTTON6(6),
    BUTTON_LAST(7),
    UNKNOWN(9999);

    private final int code;

    MouseCode(int code) {
        this.code = code;
    }
}