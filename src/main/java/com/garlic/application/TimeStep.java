package com.garlic.application;

import lombok.Getter;

@Getter
public class TimeStep {

    private double value = 0;

    public TimeStep(double start, double stop) {
        this.value = stop - start;
    }
}
