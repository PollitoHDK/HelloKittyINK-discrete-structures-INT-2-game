package com.example.animationg3.model;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class StaticBlock extends Block {
    public StaticBlock(Canvas canvas, int x, int y) {
        super(canvas, x, y);
        block = new Image(getClass().getResourceAsStream("/objects/bloque.png"), 50, 50, false, false);
    }

    @Override
    public void paint() {
        super.paint();

    }
}