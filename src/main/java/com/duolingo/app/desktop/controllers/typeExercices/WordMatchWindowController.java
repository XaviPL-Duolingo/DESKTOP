package com.duolingo.app.desktop.controllers.typeExercices;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class WordMatchWindowController implements Initializable {

    private int idLevel, idTypeExercice;

    @FXML    private Button btnCreateExercice;
    @FXML    private Button btnAddAnswer;
    @FXML    private JFXToggleButton btnIsHard;
    @FXML    private Button btnRemoveAnswer;

    @FXML    private TableView<String> tableWords;
    @FXML    private TableColumn<String, String> columnWord;
    @FXML    private TableColumn<String, String> columnMatch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void addAnswer(){

        String word = "tomate";
        String match = "tomato";

    }

    @FXML
    public void checkContent(ActionEvent actionEvent) {
    }

    private void clear(){

        tableWords.getItems().clear();



    }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }

}