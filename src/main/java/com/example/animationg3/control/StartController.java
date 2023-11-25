package com.example.animationg3.control;

import com.example.animationg3.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StartController  {
    @FXML
    public Button start;
    public ImageView titleImage;
    public ImageView background;
    public ImageView zeldaLogo;
    @FXML
    public void onClick(ActionEvent actionEvent) {
        Stage stage=(Stage) start.getScene().getWindow();
        stage.close();
        HelloApplication.battleView("hello-view.fxml");
    }
}
