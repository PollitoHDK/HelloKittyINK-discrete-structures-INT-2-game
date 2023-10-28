package com.example.javafx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MenuGame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Onichan?");
        stage.setScene(scene);
        stage.show();
    }
    public static void openWindow(String fxml){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            Stage stage=new Stage();
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }catch (IOException exception){
            exception.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}