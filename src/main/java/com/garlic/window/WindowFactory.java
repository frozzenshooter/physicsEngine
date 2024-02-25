package com.garlic.window;

import com.garlic.events.EventCallback;

public class WindowFactory {

    private WindowFactory(){
        //do nothing
    }

    public static Window create(WindowType windowType, WindowProperties properties, EventCallback callback) {
        if (windowType == WindowType.GLFW) {
            return new GlfwWindow(properties, callback);
        }
        throw new IllegalStateException("Unsupported Window Type");
    }
}
