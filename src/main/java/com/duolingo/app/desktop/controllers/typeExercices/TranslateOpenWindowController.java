package com.duolingo.app.desktop.controllers.typeExercices;

import com.duolingo.app.interfaces.impl.ExerciceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateOpenWindowController implements Initializable {

    private ExerciceImpl exerciceManager = new ExerciceImpl();
    private int idLevel, idTypeExercice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void createExercice(String phrToTranslate){

        idLevel = 1;
        idTypeExercice = 1;
        String[] contentExercice = new String[]{};

        exerciceManager.createExercice(idLevel, idTypeExercice, contentExercice);

    }

    public void setIdLevel(int idLevel){
        this.idLevel = idLevel;
    }

    public void setIdTypeExercice(int idTypeExercice){
        this.idTypeExercice = idTypeExercice;
    }

    @FXML
    public void checkContent(ActionEvent actionEvent) {
    }
}
