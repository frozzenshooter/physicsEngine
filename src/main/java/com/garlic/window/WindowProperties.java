package com.garlic.window;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WindowProperties {
    private int height = 900;
    private int width = 1600;
    private String title = "Window title";
    private boolean vSyncEnabled = true;
}
