package com.example.animationg3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static int limitX = 700;
    public static int limitY = 460;
    @Override
    public void start(Stage stage) throws IOException {
        openWindow("star-view.fxml");
    }
    public static void openWindow(String fxml){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), limitX, limitY);
            Stage stage=new Stage();
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }catch (IOException exception){
            exception.printStackTrace();
        }

    }
    public static void battleView(String fxml){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
            Stage stage=new Stage();
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}