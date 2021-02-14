package com.duolingo.app.desktop.controllers.typeExercices;

import com.duolingo.app.interfaces.impl.ExerciceImpl;
import com.jfoenix.controls.JFXToggleButton;
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
    @FXML    private TextField txtAnswer, txtWrongAnswer1, txtWrongAnswer2;
    @FXML    private JFXToggleButton btnIsHard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void checkContent(ActionEvent event) {

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

        String[] contentExercice = new String[]{question, answer, wrongAnswer1, wrongAnswer2};
        boolean isHard = btnIsHard.isSelected();

        try{
            exerciceManager.insertTypeTestExercice(idLevel, contentExercice, isHard);
            System.out.println("[DEBUG] - Ejercicio TIPO TEST creado correctamente!p");
            clear();
        }catch (Exception e){
            System.out.println("[DEBUG] - Error al crear ejercicio TIPO TEST");
        }


    }

    private void clear(){

        txtAnswer.clear();
        txtQuestion.clear();
        txtWrongAnswer1.clear();
        txtWrongAnswer2.clear();
        btnIsHard.setSelected(false);


    }

    public void setIdLevel(int idLevel){
        this.idLevel = idLevel;
    }

}