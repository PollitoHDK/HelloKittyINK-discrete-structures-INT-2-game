package com.example.animationg3.screens;

import com.example.animationg3.model.*;
import com.example.animationg3.util.UnweightedGraph;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.*;

public class ScreenA {

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Avatar avatar;
    private ArrayList<Block> blockarraylist;
    private UnweightedGraph<Block> blockGraph;
    private ArrayList<Enemy> enemies;
    private Background backgroundImage;
    private Random random;


    public ScreenA(Canvas canvas) {
        random = new Random();
        this.enemies=new ArrayList<>();
        this.blockarraylist = new ArrayList<>();
        this.blockGraph = new UnweightedGraph<>(50);
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.avatar = new Avatar(canvas);
        generatePlayableMapGraph();
        SaveEnemies();
        avatar.setBlocksforGraph(blockGraph);
        avatar.setStaticBlocks(blockarraylist);
        avatar.setEnemies(enemies);
    }

    public void paint() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        avatar.paint();
        avatar.onAction();
        avatar.update();
        avatar.generateBomb();
        // Llama a onAction después de pintar el avatar
        generateBlocksGraph();
        EnemyMove();
        EnemyChecker();
    }
    public void SaveEnemies(){
        enemies.add(new Enemy(canvas,avatar,blockarraylist));
    }
    public void EnemyMove(){
        for(Enemy enemy: enemies){
                enemy.move();
                enemy.paint();
                enemy.generateBomb();
            }
    }
    public void EnemyChecker(){
        enemies.removeIf(enemy -> !enemy.isAlive());
    }
    public void generateBlocks(){
        for (Block block : blockarraylist) {
            block.paint();
        }
    }
    private void drawBackground() {
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/backgrounds/scene1.jpg")));
        graphicsContext.drawImage(backgroundImage, 0, 0, canvas.getWidth(), canvas.getHeight());
    }
    public void generateBlocksGraph(){
        blockGraph.forEachNode(Block::paint);
    }
    public void saveBlocks(){
        //destruible Blocks
        boolean randomBoolean = false;
        for (int i = 50; i < canvas.getWidth()-50; i += 105) {
            if(!randomBoolean)
                i+=50;
            for (int j = 50; j < canvas.getHeight()-50; j += 105) {
                DestruibleBlock blocks = new DestruibleBlock(canvas,i,j);
                blockarraylist.add(blocks);
            }
        }
        for (int i = 50; i < canvas.getWidth()-50; i += 105) {
            if(!randomBoolean)
                i+=50;
            for (int j = 50; j < canvas.getHeight()-50; j += 105) {
                DestruibleBlock blocks = new DestruibleBlock(canvas,i,j);
                blockarraylist.add(blocks);
            }
        }
        //Static Blocks
        for (int i = 0; i < canvas.getWidth(); i+=50) {
            StaticBlock blocks1 = new StaticBlock(canvas,i,0);
            blockarraylist.add(blocks1);
        }
        for (int i = 0; i < canvas.getWidth(); i+=50) {
            StaticBlock blocks2 = new StaticBlock(canvas, i,(int) canvas.getHeight()-50);
            blockarraylist.add(blocks2);
        }
        for (int j = 50; j < canvas.getHeight(); j += 50) {
            StaticBlock blocks3 = new StaticBlock(canvas,0,j);
            blockarraylist.add(blocks3);
        }
        for (int j = 50; j < canvas.getHeight(); j += 50) {
            StaticBlock blocks4 = new StaticBlock(canvas,(int)canvas.getWidth()-50,j);
            blockarraylist.add(blocks4);
        }


        for (int i = 105; i < canvas.getWidth()-50; i += 105) {
            for (int j = 105; j < canvas.getHeight()-50; j += 105) {
                StaticBlock blocks = new StaticBlock(canvas,i,j);
                blockarraylist.add(blocks);
            }
        }

    }

    public void generatePlayableMapGraph() {
        // Agregar bloques destruibles en un patrón jugable
        for (int i = 100; i < canvas.getWidth() - 50; i += 200) {
            for (int j = 100; j < canvas.getHeight() - 50; j += 100) {
                // Agrega un espacio en la mitad de la columna
                if (j != (canvas.getHeight() - 50) / 2) {
                    DestruibleBlock destructibleBlock = new DestruibleBlock(canvas, i, j);
                    blockGraph.addNode(generateNodeId(i, j), destructibleBlock);
                }
            }
        }

        // Agregar bloques fijos alrededor del borde del mapa
        for (int i = 0; i < canvas.getWidth(); i += 50) {
            StaticBlock block1 = new StaticBlock(canvas, i, 0);
            blockGraph.addNode(generateNodeId(i, 0), block1);
        }
        for (int i = 0; i < canvas.getWidth(); i += 50) {
            StaticBlock block2 = new StaticBlock(canvas, i, (int) canvas.getHeight() - 50);
            blockGraph.addNode(generateNodeId(i, (int) canvas.getHeight()-50), block2);
        }
        for (int j = 50; j < canvas.getHeight(); j += 50) {
            StaticBlock block3 = new StaticBlock(canvas, 0, j);
            blockGraph.addNode(generateNodeId(0, j), block3);
        }
        for (int j = 50; j < canvas.getHeight(); j += 50) {
            StaticBlock block4 = new StaticBlock(canvas, (int) canvas.getWidth()-50, j);
            blockGraph.addNode(generateNodeId((int) canvas.getWidth()-50, j), block4);
        }
    }
    private int generateNodeId(int x, int y) {
        return x / 50 + (y / 50) * (int) (canvas.getWidth() / 50);
    }
    public void onKeyPressed(KeyEvent event){
        this.avatar.onKeyPressed(event);
    }
    public void onKeyReleased(KeyEvent event){
        this.avatar.onKeyReleased(event);
    }
}
