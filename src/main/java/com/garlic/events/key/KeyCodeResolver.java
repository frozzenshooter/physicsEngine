package com.garlic.events.key;

import java.util.HashMap;

public class KeyCodeResolver {

    private final static HashMap<Integer, KeyCode> keyCodes = new HashMap<>();

    public static void init() {
        // we initialize it like that so we have O(1) access time to resolve the key code
        for (var keyCode : KeyCode.values()) {
            keyCodes.put(keyCode.getCode(), keyCode);
        }
    }

    public static KeyCode resolve(int code) {
        var keyCode = keyCodes.get(code);
        if (keyCode == null) {
            keyCode = KeyCode.UNKNOWN;
        }
        return keyCode;
    }
}
