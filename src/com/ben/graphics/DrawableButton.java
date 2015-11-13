package com.ben.graphics;

import javafx.scene.control.Button;

public class DrawableButton extends Drawable {
    
    public String text;
    
    public DrawableButton(String text, double x, double y) {
        this.text = text;
        this.x = x;
        this.y = y;
        remakeNode();
    }
    
    public void remakeNode() {
        super.remakeNode();
        Button b = (Button)node;
        b.setText(text);
        b.setLayoutX(x);
        b.setLayoutY(y);
        node = b;
    }

}
