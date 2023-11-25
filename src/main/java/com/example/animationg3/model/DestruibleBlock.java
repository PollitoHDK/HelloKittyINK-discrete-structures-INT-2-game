package com.example.animationg3.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class DestruibleBlock extends Block {

    private Boolean destroyed;

    public DestruibleBlock(Canvas canvas, int x, int y) {
        super(canvas, x, y);
        block = new Image(getClass().getResourceAsStream("/objects/bloqueDestruible.jpg"), 50, 50, false, false);
        this.destroyed = false;
    }
    @Override
    public void paint() {
        super.paint();
    }
}
