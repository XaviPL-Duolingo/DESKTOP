package com.duolingo.app.desktop.controllers.typeExercices;

import com.duolingo.app.interfaces.impl.ExerciceImpl;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class WordFillWindowController implements Initializable {

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

        if (!question.isEmpty() &&  question.contains("%WORD%")){
            if (!answer.isEmpty() && !answer.contains(" ")){
                if (!wrongAnswer1.isEmpty() && !wrongAnswer1.contains(" ")){
                    if (!wrongAnswer2.isEmpty() && !wrongAnswer2.contains(" ")){
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
            System.out.println("[DEBUG] - Debes introducir una pregunta valida... No olvides de introducir %WORD%" +
                    "para indicar que ahi estará la palabra a completar...");
        }
    }

    private void createExercice(String question, String answer, String wrongAnswer1, String wrongAnswer2){

        String[] contentExercice = new String[]{question, answer, wrongAnswer1, wrongAnswer2};
        boolean isHard = btnIsHard.isSelected();

        try{
            exerciceManager.insertWordFillExercice(idLevel, contentExercice, isHard);
            System.out.println("[DEBUG] - Ejercicio WORD FILL creado correctamente!p");
            clear();
        }catch (Exception e){
            System.out.println("[DEBUG] - Error al crear ejercicio WORD FILL");
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