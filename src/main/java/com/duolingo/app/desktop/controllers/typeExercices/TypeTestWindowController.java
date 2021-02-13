package com.duolingo.app.desktop.controllers.typeExercices;

import com.duolingo.app.interfaces.impl.ExerciceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TypeTestWindowController implements Initializable {

    private ExerciceImpl exerciceManager = new ExerciceImpl();
    private int idLevel, idTypeExercice;

    @FXML    private TextField txtQuestion;
    @FXML    private Button btnCreateExercice;
    @FXML    private TextField txtAnswer;
    @FXML    private TextField txtWrongAnswer1;
    @FXML    private TextField txtWrongAnswer2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void checkContent(ActionEvent event) {

        System.out.println("level: "+idLevel + " // type: " + idTypeExercice);

        String question = txtQuestion.getText();
        String answer = txtAnswer.getText();
        String wrongAnswer1 = txtWrongAnswer1.getText();
        String wrongAnswer2 = txtWrongAnswer2.getText();

        if (!question.isEmpty()){
            if (!answer.isEmpty()){
                if (!wrongAnswer1.isEmpty()){
                    if (!wrongAnswer2.isEmpty()){
                        createExercice(question, answer, wrongAnswer1, wrongAnswer2);
                    }else{
                        System.out.println("[DEBUG] - Falta introducir la segunda respuesta incorrecta...");
                    }
                }else{
                    System.out.println("[DEBUG] - Falta introducir la primera respuesta incorrecta...");
                }
            }else {
                System.out.println("[DEBUG] - Debes introducir una respuesta valida...");
            }
        }else{
            System.out.println("[DEBUG] - Debes introducir una pregunta valida...");
        }
    }

    private void createExercice(String question, String answer, String wrongAnswer1, String wrongAnswer2){

        idLevel = 1;
        idTypeExercice = 1;
        String[] contentExercice = new String[]{question, answer, wrongAnswer1, wrongAnswer2};

        exerciceManager.createExercice(idLevel, idTypeExercice, contentExercice);

    }

    public void setIdLevel(int idLevel){
        this.idLevel = idLevel;
    }

    public void setIdTypeExercice(int idTypeExercice){
        this.idTypeExercice = idTypeExercice;
    }


}