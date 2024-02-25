package com.garlic.window;

import com.garlic.events.EventCallback;
import com.garlic.events.key.KeyCodeResolver;
import com.garlic.events.key.KeyPressedEvent;
import com.garlic.events.key.KeyReleasedEvent;
import com.garlic.events.mouse.*;
import com.garlic.events.window.WindowCloseEvent;
import com.garlic.events.window.WindowResizeEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

@Getter
@Log4j2
public class Window {

    private final int height;
    private final int width;
    private final String title;
    private boolean vSyncEnabled;
    private final EventCallback eventCallback;
    private final List<Callback> callbacks = new ArrayList<>();

    private long window;

    public Window(Properties properties, EventCallback callback) {
        this.width = properties.width;
        this.height = properties.height;
        this.title = properties.title;
        this.vSyncEnabled = properties.vSyncEnabled;
        this.eventCallback = callback;
    }

    public void render() {
        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }

    public void shutdown() {
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        var callback = glfwSetErrorCallback(null);
        if (callback != null) {
            callback.close();
        }
    }

    public void setVSyncEnabled(boolean vSyncEnabled) {
        this.vSyncEnabled = vSyncEnabled;

        if (vSyncEnabled) {
            glfwSwapInterval(1);
        } else {
            glfwSwapInterval(0);
        }
    }

    @Getter
    @Setter
    public static class Properties {
        private int height = 900;
        private int width = 1600;
        private String title = "Window title";
        private boolean vSyncEnabled = true;
    }

    public void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create the GLFW window");

        createCallbacks();

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);

        this.setVSyncEnabled(vSyncEnabled);

        // Make the window visible
        glfwShowWindow(window);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
    }

    private void createCallbacks() {
        KeyCodeResolver.init();
        MouseCodeResolver.init();

        var keyCallback = glfwSetKeyCallback(window, this::keyCallback);
        this.callbacks.add(keyCallback);

        var windowSizeCallback = glfwSetWindowSizeCallback(window, this::windowSizeCallback);
        this.callbacks.add(windowSizeCallback);

        var windowCloseCallback = glfwSetWindowCloseCallback(window, this::windowCloseEvent);
        this.callbacks.add(windowCloseCallback);

        var mouseCallback = glfwSetMouseButtonCallback(window, this::mouseCallback);
        this.callbacks.add(mouseCallback);

        var mouseScrollCallback = glfwSetScrollCallback(window, this::mouseScrollCallback);
        this.callbacks.add(mouseScrollCallback);

        var mouseCursorPosCallback = glfwSetCursorPosCallback(window, this::mouseCourserPositionCallback);
        this.callbacks.add(mouseCursorPosCallback);
    }

    private void mouseCourserPositionCallback(long window, double xPos, double yPos) {
        var event = new MouseMovedEvent(xPos, yPos);
        eventCallback.onEvent(event);
    }

    private void mouseScrollCallback(long window, double xOffset, double yOffset) {
        var event = new MouseScrolledEvent(xOffset, yOffset);
        eventCallback.onEvent(event);
    }

    private void mouseCallback(long window, int button, int action, int mods) {
        switch (action) {
            case GLFW_PRESS: {
                var mouseCode = MouseCodeResolver.resolve(button);
                var event = new MouseButtonPressedEvent(mouseCode);
                eventCallback.onEvent(event);
                break;
            }
            case GLFW_RELEASE: {
                var mouseCode = MouseCodeResolver.resolve(button);
                var event = new MouseButtonReleasedEvent(mouseCode);
                eventCallback.onEvent(event);
                break;
            }
        }
    }

    private void windowCloseEvent(long l) {
        var event = new WindowCloseEvent();
        eventCallback.onEvent(event);
    }

    private void keyCallback(long window, int key, int scancode, int action, int mods) {
        switch (action) {
            case GLFW_PRESS: {
                var keyCode = KeyCodeResolver.resolve(key);
                var event = new KeyPressedEvent(keyCode, false);
                eventCallback.onEvent(event);
                break;
            }
            case GLFW_RELEASE: {
                var keyCode = KeyCodeResolver.resolve(key);
                var event = new KeyReleasedEvent(keyCode);
                eventCallback.onEvent(event);
                break;
            }
            case GLFW_REPEAT: {
                var keyCode = KeyCodeResolver.resolve(key);
                var event = new KeyPressedEvent(keyCode, true);
                eventCallback.onEvent(event);
            }
        }
    }

    private void windowSizeCallback(long window, int width, int height) {
        var event = new WindowResizeEvent(width, height);
        this.eventCallback.onEvent(event);
    }
}
