package com.example.animationg3.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.ResourceBundle;
import com.example.animationg3.screens.ScreenA;


public class BattleController implements Initializable {
    private static BattleController instance;
    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext graphicsContext;
    @FXML
    private ScreenA screenA;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.graphicsContext = canvas.getGraphicsContext2D();
        this.screenA = new ScreenA(this.canvas);

        this.canvas.setOnKeyPressed(event -> {
            screenA.onKeyPressed(event);
        });
        this.canvas.setOnKeyReleased(event -> {
            screenA.onKeyReleased(event);
        });
        // Hilo de Java


        new Thread(
                () -> {
                    while (true) {
                        Platform.runLater(() -> {
                            screenA.paint();
                        });
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }


}