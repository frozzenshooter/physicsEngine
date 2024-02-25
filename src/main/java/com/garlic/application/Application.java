package com.garlic.application;

import com.garlic.events.Event;
import com.garlic.events.EventDispatcher;
import com.garlic.events.EventType;
import com.garlic.events.key.KeyCode;
import com.garlic.events.key.KeyReleasedEvent;
import com.garlic.window.Window;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Application {

    private static Application instance;
    private Window window;
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

        var props = new Window.Properties();
        this.window = new Window(props, this::onEvent);
        this.window.init();
    }

    private void registerEventListeners() {
        this.eventDispatcher.registerEventListener(EventType.WINDOW_RESIZE, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.WINDOW_CLOSE, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.KEY_RELEASED, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.KEY_RELEASED, this::closeWindowHandling);
        this.eventDispatcher.registerEventListener(EventType.KEY_PRESSED, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.MOUSE_BUTTON_PRESSED, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.MOUSE_BUTTON_RELEASED, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.MOUSE_SCROLLED, this::logEvent);
        this.eventDispatcher.registerEventListener(EventType.MOUSE_MOVED, this::logEvent);
    }

    private void closeWindowHandling(Event event) {
        if (event instanceof KeyReleasedEvent keyReleasedEvent) {
            if (keyReleasedEvent.getKeyCode() == KeyCode.ESCAPE) {
                window.setWindowShouldClose();
            }
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
            var timeStep = new TimeStep(currentFrameTime, lastFrameTime);
            lastFrameTime = currentFrameTime;

            update(timeStep);

            render();

            running = window.shouldClose();
        }

        shutdown();
        log.info("Stopped");
    }

    private void onEvent(Event event) {
        eventDispatcher.dispatch(event);
    }

    private void update(TimeStep deltaTime) {
        //do nothing
    }

    private void render() {
        window.render();
    }

    private void shutdown() {
        window.shutdown();
    }
}