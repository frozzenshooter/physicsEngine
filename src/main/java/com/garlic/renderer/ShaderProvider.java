package com.garlic.renderer;

import java.util.HashMap;
import java.util.Optional;

public class ShaderProvider {

    private final HashMap<String, Shader> shaders = new HashMap<>();

    public String load(String filePath) {

        var shader = this.loadShader(filePath);
        shaders.put(shader.getName(), shader);

        return shader.getName();
    }

    private Shader loadShader(String filePath) {
        //TODO: shader loading from file maybe from jar/resources?
        return null;
    }

    public Optional<Shader> get(String name) {
        return Optional.ofNullable(shaders.get(name));
    }
}
