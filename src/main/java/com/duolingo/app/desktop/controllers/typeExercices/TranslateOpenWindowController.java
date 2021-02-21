package com.duolingo.app.desktop.controllers.typeExercices;

import com.duolingo.app.interfaces.impl.ExerciceImpl;
import com.duolingo.app.model.Exercice;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TranslateOpenWindowController implements Initializable {

    private ExerciceImpl exerciceManager = new ExerciceImpl();
    private int idLevel;
    private boolean isListen;

    @FXML    private TextField txtQuestion;
    @FXML    private JFXToggleButton btnIsHard;
    @FXML    private Button btnCreateExercice, btnAddAnswer, btnRemoveAnswer;
    @FXML    private ListView<String> listAnswers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setIdLevel(int idLevel){
        this.idLevel = idLevel;
    }

    public void showData(int idExercice){

        Exercice exerciceObj = exerciceManager.getExerciceByID(idExercice);
        JSONObject objectJSON = new JSONObject(exerciceObj.getContentExercice());

        txtQuestion.setText((String) objectJSON.get("phrToTranslate"));
        btnIsHard.setSelected(exerciceObj.isHard());

        try {
            int i = 1;
            for (i = 1; i < 100; i++){
                listAnswers.getItems().add((String) objectJSON.get("answer"+ i));
            }
        }catch (Exception e){
            System.out.println("[DEBUG] - Bucle FOR [answerX] terminado!");
        }

        btnIsHard.setDisable(true);
        listAnswers.setDisable(true);
        btnCreateExercice.setDisable(true);
        btnAddAnswer.setDisable(true);
        btnRemoveAnswer.setDisable(true);
        txtQuestion.setDisable(true);
    }

    @FXML
    public void checkContent(ActionEvent actionEvent) {

        String question = txtQuestion.getText();
        List<String> listPhrAnswers = listAnswers.getItems();
        listPhrAnswers.add(0, question);

        if (!question.isEmpty()){
            if (listPhrAnswers.size() >= 2){
                createExercice(listPhrAnswers);
            }else{
                System.out.println("[DEBUG] - No hay suficientes respuestas válidas...");
            }
        }else {
            System.out.println("[DEBUG] - La frase a traducir no es valida...");
        }

    }

    @FXML
    public void addAnswer(){
        try {
            String phrTranslated = JOptionPane.showInputDialog("Añadir una respuesta:");
            if (!phrTranslated.isEmpty()){
                listAnswers.getItems().add(phrTranslated);
                System.out.println("[DEBUG] - Frase correcta añadida correctamente!");
            }else {
                System.out.println("[DEBUG] - La respuesta introducida no es valida...");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void removeAnswer(){
        try {
            listAnswers.getSelectionModel().getSelectedIndices().removeAll();
            System.out.println("[DEBUG] - Respuesta/s eliminada/s correctamente!");
        }catch (Exception e){
            System.out.println("[DEBUG] - No hay items seleccionados...");
        }
    }

    private void createExercice(List<String> listPhrAnswers){

        boolean isHard = btnIsHard.isSelected();

        try {
            exerciceManager.insertTranslateExercice(idLevel, listPhrAnswers.toArray(new String[listPhrAnswers.size()]), isHard, isListen);
            System.out.println("[DEBUG] - Ejercicio TRANSLATE OPEN creado correctamente!");
            clear();
        }catch (Exception e){
            System.out.println("[DEBUG] - Error al crear ejercicio TRANSLATE OPEN");
        }

    }

    private void clear(){
        txtQuestion.clear();
        listAnswers.getItems().clear();
    }

    public void setListen(boolean listen) {
        isListen = listen;
    }
}
