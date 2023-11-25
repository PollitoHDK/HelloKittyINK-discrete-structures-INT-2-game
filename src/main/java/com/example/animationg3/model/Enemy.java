package com.example.animationg3.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Enemy {
    private Canvas canvas;
    private Position position;
    private int frame;
    private GraphicsContext graphicsContext;
    private State state;
    private Avatar avatar;
    private Image sprite;
    private ArrayList<Block> blocks;
    private int health;
    private boolean alive;

    public Enemy(Canvas canvas, Avatar avatar, ArrayList<Block> blocks) {
        this.health=3;
        this.blocks = blocks;
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.frame = 0;
        this.position = new Position(120, 60);
        this.avatar = avatar;
        sprite = new Image(getClass().getResourceAsStream("/animations/enemy/test.png"), 50, 50, false, false);
        this.alive=true;
    }

    public void move() {
        double radius = 300; // Define the detection radius

        if (isPlayerWithinRadius(radius)) {
            moveToPlayer();
        } else if (!isPlayerWithinRadius(radius)){
            moveInSquarePattern();
        }
    }
    private double calculateDistance(Position playerPosition, Position enemyPosition) {
        double deltaX = enemyPosition.getX() - playerPosition.getX();
        double deltaY = enemyPosition.getY() - playerPosition.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    private boolean isPlayerWithinRadius(double radius) {
        double distance = calculateDistance(avatar.getPosition(), position);
        return distance <= radius;
    }
    private void moveToPlayer() {
            if (position.getX() < avatar.getPosition().getX()) {
                Right();
            } else if (position.getX() > avatar.getPosition().getX()) {
                Left();
            } else if (position.getY() < avatar.getPosition().getY()) {
                Down();
            } else if (position.getY() > avatar.getPosition().getY()) {
                Up();
            }
    }



    private void Right() {
        for (Block block : blocks) {
            if (position.getX() + 40 < block.getPosition().getX() &&
                    position.getX() + 50 >= block.getPosition().getX() &&
                    position.getY() + 40 >= block.getPosition().getY() &&
                    position.getY() <= block.getPosition().getY() + 40) {
                // Colisión hacia la derecha
                return;
            }
        }
        if (position.getX() < canvas.getWidth() - 40) {
            position.setX(position.getX() + 10);
        }
    }

    private void Left() {
        for (Block block : blocks) {
            if (position.getX() > block.getPosition().getX() + 40 &&
                    position.getX() <= block.getPosition().getX() + 50 &&
                    position.getY() + 40 >= block.getPosition().getY() &&
                    position.getY() <= block.getPosition().getY() + 40) {
                return;
            }
        }
        if (position.getX() > 0) {
            position.setX(position.getX() - 8);
        }
    }
    private void Down() {
        for (Block block : blocks) {
            if (position.getY() + 40 < block.getPosition().getY() &&
                    position.getY() + 50 >= block.getPosition().getY() &&
                    position.getX() + 40 >= block.getPosition().getX() &&
                    position.getX() <= block.getPosition().getX() + 40) {
                // Colisión hacia abajo
                return;
            }
        }
        if (position.getY() < canvas.getHeight() - 40) {
            position.setY(position.getY() + 8);
        }
    }

    private void Up() {
        for (Block block : blocks) {
            if (position.getY() > block.getPosition().getY() + 45 &&
                    position.getY() <= block.getPosition().getY() + 55 &&
                    position.getX() + 45 >= block.getPosition().getX() &&
                    position.getX() <= block.getPosition().getX() + 45) {
                // Colisión hacia arriba
                return;
            }
        }
        if (position.getY() > 0) {
            position.setY(position.getY() - 8);
        }
    }
    public void paint(){
        graphicsContext.drawImage(sprite, position.getX(), position.getY());
    }
    public void setStaticBlocks(ArrayList<Block> Blocks) {
        this.blocks = Blocks;
    }
    private void moveInSquarePattern() {
        int step = 7; // Define the step size for each movement in the square pattern

        // Move in a square pattern until the player is within the detection radius
        if (position.getX() < canvas.getWidth() - step && position.getY() == 0) {
            Right();
        } else if (position.getX() == canvas.getWidth() - step && position.getY() < canvas.getHeight() - step) {
            Down();
        } else if (position.getX() > 0 && position.getY() == canvas.getHeight() - step) {
            Left();
        } else if (position.getX() == 0 && position.getY() > 0) {
            Up();
        }
    }

    public Position getPosition() {
        return position;
    }
    public void receiveDamage(){
        this.health-=1;
        System.out.println("Mi vida es "+health);
    }

    public int getHealth() {
        return health;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }
}
