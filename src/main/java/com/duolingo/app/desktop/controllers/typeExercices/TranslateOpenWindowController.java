package com.duolingo.app.desktop.controllers.typeExercices;

import com.duolingo.app.interfaces.impl.ExerciceImpl;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateOpenWindowController implements Initializable {

    private ExerciceImpl exerciceManager = new ExerciceImpl();
    private int idLevel;
    private boolean isListen;

    @FXML    private TextField txtQuestion;
    @FXML    private JFXToggleButton btnIsHard;
    @FXML    private ListView<String> listAnswers;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setIdLevel(int idLevel){
        this.idLevel = idLevel;
    }

    @FXML
    public void checkContent(ActionEvent actionEvent) {

    }

    @FXML
    public void addAnswer(){

    }

    private void createExercice(){



    }

    private void clear(){
        txtQuestion.clear();
        listAnswers.getItems().clear();
    }

    public void setListen(boolean listen) {
        isListen = listen;
    }
}
