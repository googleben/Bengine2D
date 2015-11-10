package com.ben.graphics;

import javafx.scene.input.KeyCode;

@FunctionalInterface
public interface KeyHandler {
    
    public void handle(KeyCode e);
    
}
