package com.duolingo.app.desktop.controllers.typeExercices;

import com.duolingo.app.interfaces.impl.ExerciceImpl;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class WordMatchWindowController implements Initializable {

    private ExerciceImpl exerciceManager = new ExerciceImpl();
    private int idLevel;

    @FXML    private Button btnCreateExercice;
    @FXML    private Button btnAddAnswer;
    @FXML    private JFXToggleButton btnIsHard;
    @FXML    private Button btnRemoveAnswer;

    @FXML    private TableView<WordMatch> tableWords;
    @FXML    private TableColumn<String, String> columnWord;
    @FXML    private TableColumn<String, String> columnMatch;

    public static class WordMatch {

        private String word;
        private String match;

        public WordMatch(String word, String match) {
            this.word = word;
            this.match = match;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getMatch() {
            return match;
        }

        public void setMatch(String match) {
            this.match = match;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        columnWord.setCellValueFactory(new PropertyValueFactory<>("word"));
        columnMatch.setCellValueFactory(new PropertyValueFactory<>("match"));

    }

    @FXML
    public void addAnswer(ActionEvent event){

        try {
            String wordCol1 = JOptionPane.showInputDialog("Palabra en idioma a traducir:");
            String wordCol2 = JOptionPane.showInputDialog("Palabra traducida:");
            WordMatch wordMatchObj = new WordMatch(wordCol1, wordCol2);
            tableWords.getItems().add(wordMatchObj);
            System.out.println("[DEBUG] - Pareja añadida correctamente!");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    public void removeAnswer(ActionEvent event) {

        // removeAnswer()
        // Obtiene todos los items de la tabla y los seleccionados y si uno de los items seleccionados
        // esta en la lista de todos los items de la tabla, lo elimina.

        ObservableList<WordMatch> allMatches, selectedMatches;
        allMatches = tableWords.getItems();
        selectedMatches = tableWords.getSelectionModel().getSelectedItems();
        selectedMatches.forEach(allMatches::remove);

    }

    @FXML
    public void checkContent(ActionEvent actionEvent) {

        if (!tableWords.getItems().isEmpty()){
            createExercice();
        }else {
            System.out.println("`DEBUG] - No hay suficientes MATCH WORDS añadidas...");
        }

    }

    private void createExercice(){

        ObservableList<WordMatch> wordMatchesList = tableWords.getItems();
        String[] contentExercice = wordMatchesList.toArray(new String[wordMatchesList.size()]);
        boolean isHard = btnIsHard.isSelected();

        // exerciceManager.insertWordMatchExercice(idLevel, contentExercice, isHard);

    }

    private void clear(){

        tableWords.getItems().clear();

    }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }

}

