package com.example.animationg3.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;

public class Bomb {
    protected Canvas canvas;
    protected GraphicsContext graphicsContext;
    protected  Position position;
    private ArrayList<Image> bomb;
    private long starTime;

    public Bomb(Canvas canvas, int x, int y) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.position = new Position(x, y);
        this.bomb = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/animations/bomb/bomb" + i + ".png")), 45, 45, false, false);
            this.bomb.add(image);
        }
        starTime = System.currentTimeMillis();
    }
    public void paint(){
        if(System.currentTimeMillis() - starTime < 1800){
            graphicsContext.drawImage(bomb.get(0), position.getX(), position.getY());
        } else if (System.currentTimeMillis() - starTime < 1800) {
            graphicsContext.drawImage(bomb.get(1), position.getX(), position.getY());
        } else if (System.currentTimeMillis() - starTime < 1900) {
            graphicsContext.drawImage(bomb.get(2), position.getX(), position.getY());
        } else if (System.currentTimeMillis() - starTime < 2000) {
            graphicsContext.drawImage(bomb.get(3), position.getX(), position.getY());
        } else if (System.currentTimeMillis() - starTime < 2100) {
            graphicsContext.drawImage(bomb.get(4), position.getX(), position.getY());
        }
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }
}
