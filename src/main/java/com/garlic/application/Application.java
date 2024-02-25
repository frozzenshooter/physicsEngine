package com.garlic.application;

import com.garlic.events.Event;
import com.garlic.events.EventDispatcher;
import com.garlic.events.EventType;
import com.garlic.events.key.KeyCode;
import com.garlic.events.key.KeyReleasedEvent;
import com.garlic.events.window.WindowCloseEvent;
import com.garlic.renderer.Renderer;
import com.garlic.renderer.Renderer2D;
import com.garlic.window.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Application {

    private static Application instance;
    private Window window;
    private Renderer renderer;
    private boolean running;
    private final EventDispatcher eventDispatcher;

    public static Application getInstance() {

        if (instance == null) {
            instance = new Application();
        }

        return instance;
    }

    private Application() {
        this.running = true;
        eventDispatcher = new EventDispatcher();
    }

    public void init() {

        this.registerEventListeners();

        var props = new WindowProperties();
        this.window = WindowFactory.create(WindowType.GLFW, props, this::onEvent);
        this.window.init();

        this.renderer = new Renderer2D();
    }

    private void registerEventListeners() {
        this.eventDispatcher.registerEventListener(EventType.WINDOW_RESIZE, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.WINDOW_CLOSE, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.WINDOW_CLOSE, this::closeWindowIfNecessary);
        this.eventDispatcher.registerEventListener(EventType.KEY_RELEASED, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.KEY_RELEASED, this::closeWindowIfNecessary);
        this.eventDispatcher.registerEventListener(EventType.KEY_PRESSED, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.MOUSE_BUTTON_PRESSED, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.MOUSE_BUTTON_RELEASED, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.MOUSE_SCROLLED, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.MOUSE_MOVED, this::logEvent);
    }

    private void closeWindowIfNecessary(Event event) {
        if (event instanceof KeyReleasedEvent keyReleasedEvent) {
            if (keyReleasedEvent.getKeyCode() == KeyCode.ESCAPE) {
                this.running = false;
            }
        } else if (event instanceof WindowCloseEvent) {
            this.running = false;
        }
    }

    private void logEvent(Event event) {
        log.info(event.getDescription());
    }


    public void run() {
        log.info("Running");
        double lastFrameTime = Time.getTime();

        while (running) {
            var currentFrameTime = Time.getTime();
            var timeStep = new TimeStep(lastFrameTime, currentFrameTime);
            lastFrameTime = currentFrameTime;

            logFps(timeStep);

            update(timeStep);

            render();
        }

        shutdown();
        log.info("Stopped");
    }

    private void logFps(TimeStep timeStep) {
        double fps = 1 / timeStep.getValue();
        log.info("time taken for frame: " + timeStep.getValue() + "; FPS: " + fps);
    }

    private void onEvent(Event event) {
        eventDispatcher.dispatch(event);
    }

    private void update(TimeStep deltaTime) {
        //do nothing
    }

    private void render() {
        renderer.render();

        window.swapBuffers();
        window.pollEvents();
    }

    private void shutdown() {
        window.shutdown();

        renderer.shutdown();
        this.eventDispatcher.shutdown();
    }
}