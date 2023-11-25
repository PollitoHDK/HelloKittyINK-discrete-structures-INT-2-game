package com.example.animationg3.model;

import com.example.animationg3.util.UnweightedGraph;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Avatar {
    // elementos graficos
    private int health;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList<Image> idlesRight;
    private ArrayList<Image> runsRight;
    private ArrayList<Image> runsLeft;
    private ArrayList<Image> idlesLeft;
    private ArrayList<Image> attacksLeft;
    private ArrayList<Image> attacksRight;
    private int frame;
    private Position position;
    private State state;
    private boolean canAttack = true;
    private long lastAttackTime = 0;
    private static final long ATTACK_COOLDOWN = 1000;
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean xPressed;
    private ArrayList<Block> blocks;
    private UnweightedGraph<Block> blocksforGraph;
    private List<Integer> nodesWithStaticBlocks;
    private ArrayList<Enemy> enemies;
    private static final double swordRange=50.0;
    private Bomb[] bombs;
    private Bomb bomb;
    private boolean putBomb;
    private ArrayList<Image> link;
    private long starTime;
    public Avatar(Canvas canvas){
        this.bombs = new Bomb[50];
        this.health=5;
        this.enemies= new ArrayList<>();
        this.blocks = new ArrayList<>();
        this.blocksforGraph = new UnweightedGraph<>(50);
        this.link = new ArrayList<>();

        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        // 0 is idle | 1 is run
        this.state = State.IDLERIGHT;
        this.frame = 0;
        this.idlesLeft= new ArrayList<>();
        this.idlesRight = new ArrayList<>();
        this.runsRight = new ArrayList<>();
        this.runsLeft = new ArrayList<>();
        this.attacksLeft=new ArrayList<>();
        this.attacksRight=new ArrayList<>();

        this.position = new Position(60,60);

        for (int i = 1; i <=9; i++) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/animations/hero/idleright/Link-Idle-Right-" + i + "-sized.png")),45,45,false,false);
            this.idlesRight.add(image);
        }
        for (int i = 1; i <=9; i++) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/animations/hero/idleleft/Link-Idle-Left-" + i + "-sized.png")),45,45,false,false);
            this.idlesLeft.add(image);
        }
        for (int i = 1; i <=7 ; i++) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/animations/hero/runRight/Link-Run-Right-sized-" + i + ".png")),45,45,false,false);
            this.runsRight.add(image);
        }
        for (int i = 1; i <=7 ; i++) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/animations/hero/runLeft/Link-Run-Left-sized-" + i + ".png")),45,45,false,false);
            this.runsLeft.add(image);
        }
        for (int i=1; i<=5 ; i++){
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/animations/hero/attackRight/Link-Attack-Right-" + i + "-sized.png")),45,45,false,false);
            this.attacksRight.add(image);
        }
        for (int i = 1; i <= 4; i++) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/animations/hero/putBomb/link"+i+".png")), 45, 45, false, false);
            this.link.add(image);
        }
        this.bomb = new Bomb(canvas,(int)position.getX(),(int)position.getY());
    }
    public void paint() {
            onAction();
        if (state.equals(State.IDLERIGHT)) {
            graphicsContext.drawImage(idlesRight.get(frame % 9), position.getX(), position.getY());
            frame++;
        } else if (state.equals(State.RUNRIGHT)) {
            graphicsContext.drawImage(runsRight.get(frame % 7), position.getX(), position.getY());
            frame++;
        } else if (state.equals(State.RUNUP)) {
            graphicsContext.drawImage(runsRight.get(frame % 7), position.getX(), position.getY());
            frame++;
        } else if (state.equals(State.IDLELEFT)) {
            graphicsContext.drawImage(idlesLeft.get(frame % 9), position.getX(), position.getY());
            frame++;
        } else if (state.equals(State.RUNDOWN)) {
            graphicsContext.drawImage(runsRight.get(frame % 7), position.getX(), position.getY());
            frame++;
        }else if(state.equals(State.RUNLEFT)){
            graphicsContext.drawImage(runsLeft.get(frame % 7),position.getX(), position.getY());
            frame++;
        }else if(state.equals(State.ATTACKLEFT)){
            graphicsContext.drawImage(attacksLeft.get(frame%5),position.getX(),position.getY());
            frame++;
        }else if(state.equals(State.ATTACKRIGHT)){
            graphicsContext.drawImage(attacksRight.get(frame%5),position.getX(),position.getY());
            frame++;
        }
    }
    public void onKeyPressed(KeyEvent event){
        switch (event.getCode()){
            case RIGHT : {
                state = State.RUNRIGHT;
                rightPressed = true;
                break;
            }
            case LEFT : {
                state = State.RUNLEFT;
                leftPressed = true;
                break;
            }
            case UP : {
                state = State.RUNRIGHT;
                upPressed = true;
                break;
            }
            case DOWN : {
                state = State.RUNRIGHT;
                downPressed = true;
                break;
            }
            case X: {
                if (state == State.RUNRIGHT || state==State.IDLERIGHT){
                    state = State.ATTACKRIGHT;
                } else if (state==State.RUNLEFT || state==State.IDLELEFT){
                    state = State.ATTACKLEFT;
                } else{
                    state = State.ATTACKRIGHT;
                }
                xPressed=true;
                break;
            }
            case SPACE: {
                state = State.PUTBOMB;
                putBomb = true;
                break;
            }
        }
    }
    public void generateBomb() {
        if (putBomb) {
            for (int i = 0; i < bombs.length; i++) {
                if (bombs[i] == null && state.equals(State.PUTBOMB)) {
                    bomb = new Bomb(canvas, (int) position.getX(), (int) position.getY());
                    bombs[i] = bomb;
                    putBomb = false;
                    starTime = System.currentTimeMillis();
                    break;
                }
            }
        }
        // Pintar las bombas existentes
        for (int i = 0; i < bombs.length; i++) {
            if (bombs[i] != null) {
                if(System.currentTimeMillis() - starTime < 500){
                    graphicsContext.drawImage(link.get(0), position.getX(),position.getY());
                }else if(System.currentTimeMillis() - starTime < 650){
                    graphicsContext.drawImage(link.get(1), position.getX(),position.getY());
                } else if(System.currentTimeMillis() - starTime < 750){
                    graphicsContext.drawImage(link.get(2), position.getX(),position.getY());
                }else if(System.currentTimeMillis() - starTime < 850){
                    graphicsContext.drawImage(link.get(3), position.getX(),position.getY());
                } else if(System.currentTimeMillis() - starTime > 900){
                    bombs[i].paint();
                }
            }
        }
    }

    public void swordAttack() {
        long currentTime = System.currentTimeMillis();
        if (canAttack && (currentTime - lastAttackTime >= ATTACK_COOLDOWN)) {
            for (Enemy enemy : enemies) {
                double distance = calculateDistance(position, enemy.getPosition());
                if (distance <= swordRange) {
                    enemy.receiveDamage();
                    if (enemy.getHealth()==0){
                        enemy.setAlive(false);
                    }
                }
            }
            canAttack = false;
            lastAttackTime = currentTime;
        }
    }
    public void onKeyReleased(KeyEvent event){
        switch (event.getCode()) {
            case RIGHT: {
                state = State.IDLERIGHT;
                rightPressed = false;
                break;
            }
            case LEFT: {
                state=State.IDLELEFT;
                leftPressed = false;
                break;
            }
            case UP: {
                state = State.IDLERIGHT;
                upPressed = false;
                break;
            }
            case DOWN: {
                state=State.IDLERIGHT;
                downPressed = false;
                break;
            } case X: {
                if(state==State.ATTACKRIGHT){
                    state=State.IDLERIGHT;
                }else if(state==State.ATTACKLEFT){
                    state=State.IDLELEFT;
                }
                xPressed=false;
                break;
            }
        }
    }
    public void onAction() {
        if (rightPressed) {
            moveRight();
        } else if (leftPressed) {
            moveLeft();
        } else if (downPressed) {
            moveDown();
        } else if (upPressed) {
            moveUp();
        }else if(xPressed){
            swordAttack();
        }
    }
    private void destroyBlocks(){
        for (int i = 0; i < blocks.size(); i++) {
            if(bomb.getPosition().getX() + 40 < blocks.get(i).getPosition().getX()){
                blocks.get(i).paintRemove();
                blocks.remove(i);
            }
        }
    }
    private void moveRight() {
        AtomicBoolean canMoveRight = new AtomicBoolean(true);

        blocksforGraph.forEachNode(block -> {
            if (position.getX() + 40 < block.getPosition().getX() &&
                    position.getX() + 50 >= block.getPosition().getX() &&
                    position.getY() + 40 >= block.getPosition().getY() &&
                    position.getY() <= block.getPosition().getY() + 40) {
                // Colisión hacia la derecha
                canMoveRight.set(false);
            }
        });

        if (canMoveRight.get() && position.getX() < canvas.getWidth() - 40) {
            position.setX(position.getX() + 7);
        }
    }


    private void moveLeft() {
        AtomicBoolean canMoveLeft = new AtomicBoolean(true);

        blocksforGraph.forEachNode(block -> {
            if (position.getX() > block.getPosition().getX() + 40 &&
                    position.getX() <= block.getPosition().getX() + 50 &&
                    position.getY() + 40 >= block.getPosition().getY() &&
                    position.getY() <= block.getPosition().getY() + 40) {
                // Colisión hacia la izquierda
                canMoveLeft.set(false);
            }
        });

        if (canMoveLeft.get() && position.getX() > 0) {
            position.setX(position.getX() - 7);
        }
    }

    private void moveDown() {
        AtomicBoolean canMoveDown = new AtomicBoolean(true);

        blocksforGraph.forEachNode(block -> {
            if (position.getY() + 40 < block.getPosition().getY() &&
                    position.getY() + 50 >= block.getPosition().getY() &&
                    position.getX() + 40 >= block.getPosition().getX() &&
                    position.getX() <= block.getPosition().getX() + 40) {
                // Colisión hacia abajo
                canMoveDown.set(false);
            }
        });

        if (canMoveDown.get() && position.getY() < canvas.getHeight() - 40) {
            position.setY(position.getY() + 7);
        }
    }

    private void moveUp() {
        AtomicBoolean canMoveUp = new AtomicBoolean(true);

        blocksforGraph.forEachNode(block -> {
            if (position.getY() > block.getPosition().getY() + 40 &&
                    position.getY() <= block.getPosition().getY() + 50 &&
                    position.getX() + 40 >= block.getPosition().getX() &&
                    position.getX() <= block.getPosition().getX() + 40) {
                // Colisión hacia arriba
                canMoveUp.set(false);
            }
        });

        if (canMoveUp.get() && position.getY() > 0) {
            position.setY(position.getY() - 7);
        }
    }
    public void setStaticBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }
    public void setBlocksforGraph(UnweightedGraph<Block> blocksforGraph) {
        this.blocksforGraph = blocksforGraph;
    }
    public Position getPosition() {
        return position;
    }
    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies= enemies;
    }
    private double calculateDistance(Position playerPosition, Position enemyPosition) {
        double deltaX = enemyPosition.getX() - playerPosition.getX();
        double deltaY = enemyPosition.getY() - playerPosition.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    public void update() {
        long currentTime = System.currentTimeMillis();
        if (!canAttack && (currentTime - lastAttackTime >= ATTACK_COOLDOWN)) {
            canAttack = true;
        }
    }

}
