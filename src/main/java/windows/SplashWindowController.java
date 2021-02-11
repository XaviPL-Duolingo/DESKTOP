package windows;

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
                            Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
                            Scene scene = new Scene(root);
                            scene.setFill(Color.TRANSPARENT);
                            scene.getStylesheets().add("windows/mainWindow.css");


                            Stage stage = new Stage();
                            stage.getIcons().add(new Image(SplashWindowController.class.getResourceAsStream("res/iconApp.png")));
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
