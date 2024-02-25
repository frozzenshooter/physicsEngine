package com.garlic.events.mouse;

import java.util.HashMap;

public class MouseCodeResolver {

    private final static HashMap<Integer, MouseCode> mouseCodes = new HashMap<>();

    public static void init() {
        // we initialize it like that so we have O(1) access time to resolve the key code
        for (var mouseCode : MouseCode.values()) {
            mouseCodes.put(mouseCode.getCode(), mouseCode);
        }
    }

    public static MouseCode resolve(int code) {
        var mouseCode = mouseCodes.get(code);
        if (mouseCode == null) {
            mouseCode = MouseCode.UNKNOWN;
        }
        return mouseCode;
    }

}
