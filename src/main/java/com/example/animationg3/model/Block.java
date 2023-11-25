package com.example.animationg3.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Block {
    protected Canvas canvas;
    protected GraphicsContext graphicsContext;
    protected  Position position;
    protected Image block;
    public Block(Canvas canvas, int x, int y){
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.position = new Position(x,y);
    }
    public Position getPosition() {
        return position;
    }
    public void paint(){
        graphicsContext.drawImage(block, position.getX(), position.getY());
    }
    public void paintRemove() {
        graphicsContext.drawImage(block,position.getX(), position.getY());
    }

}
