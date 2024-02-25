package com.garlic.window;

public interface Window {

    void init();

    void shutdown();

    void setVSyncEnabled(boolean vSyncEnabled);

    void swapBuffers();

    void pollEvents();
}
