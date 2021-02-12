package com.duolingo.app.desktop.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashWindowController implements Initializable {

    @FXML
    private StackPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new SplashScreen().start();
    }


    class SplashScreen extends Thread{

        @Override
        public void run(){

            try {
                Thread.sleep(500);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new File("src/main/java/com/duolingo/app/desktop/windows/mainWindow.fxml").toURI().toURL();
                            Parent root = FXMLLoader.load(url);
                            Scene scene = new Scene(root);
                            scene.setFill(Color.TRANSPARENT);
                            url = new File("src/main/java/com/duolingo/app/desktop/windows/mainWindow.css").toURI().toURL();
                            scene.getStylesheets().add(String.valueOf(url));

                            Stage stage = new Stage();
                            url = new File("src/main/java/com/duolingo/app/desktop/res/iconApp.png").toURI().toURL();
                            Image icon = new Image(String.valueOf(url));
                            stage.getIcons().add(icon);
                            stage.setTitle("Buholingo | DESKTOP");
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.setScene(scene);
                            stage.show();

                            rootPane.getScene().getWindow().hide();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
