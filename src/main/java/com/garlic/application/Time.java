package com.garlic.application;

import org.lwjgl.glfw.GLFW;

public class Time {
    public static double getTime() {
        return GLFW.glfwGetTime();
    }
}
