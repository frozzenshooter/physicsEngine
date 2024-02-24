package com.garlic;

import com.garlic.application.Application;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class App {

    public static void main(String[] args) {

        var application = Application.getInstance();
        application.init();
        application.run();

    }
}
