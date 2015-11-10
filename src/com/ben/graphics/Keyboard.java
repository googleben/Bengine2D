package com.ben.graphics;

import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard {
	
	public HashMap<KeyCode,Boolean> pressed;
	
	public Keyboard() {
		this.pressed = new HashMap<KeyCode,Boolean>();
		for (KeyCode c : KeyCode.values()) {
			pressed.put(c,false);
		}
	}
	
	public void keyPressed(KeyCode c) {
		pressed.put(c, true);
	}
	
	public void keyReleased(KeyCode c) {
		pressed.put(c, false);
	}
	
	public EventHandler<KeyEvent> mainHandler = (e) -> {
		if (e.getEventType().equals(KeyEvent.KEY_PRESSED)) keyPressed(e.getCode());
		if (e.getEventType().equals(KeyEvent.KEY_RELEASED))keyReleased(e.getCode());
	};
	
	public boolean isPressed(KeyCode c) {
		return pressed.get(c);
	}
	
}
