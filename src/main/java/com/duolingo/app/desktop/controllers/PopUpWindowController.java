package com.duolingo.app.desktop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PopUpWindowController implements Initializable {

    private double x, y;

    @FXML    private AnchorPane rootPane;
    @FXML    private Label lblContext;
    @FXML    private TextField txPrompt;
    @FXML    private Button btnAbort;
    @FXML    private Button btnSend;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    void checkPrompt(MouseEvent event) {
        String userPrompt = txPrompt.getText();
        if (!userPrompt.isEmpty()){
            sendPrompt(userPrompt, event);
        }else {
            System.out.println("[DEBUG] - El texto introducido no es válido...");
        }
    }

    public String sendPrompt(String userPrompt, MouseEvent event){
        System.out.println("[DEBUG] - Texto introducido correctamente!");
        close(event);
        return userPrompt;
    }

    public void setContext(String context) {
        lblContext.setText(context);
    }

    @FXML
    void close(MouseEvent event){
        rootPane.getScene().getWindow().hide();
    }

    @FXML
    void minimize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void windowDrag(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);

    }

    @FXML
    void windowPressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }


}
