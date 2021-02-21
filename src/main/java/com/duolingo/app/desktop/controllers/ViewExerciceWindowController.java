package com.duolingo.app.desktop.controllers;

import com.duolingo.app.desktop.controllers.typeExercices.*;
import com.duolingo.app.interfaces.impl.ExerciceImpl;
import com.duolingo.app.model.Exercice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewExerciceWindowController implements Initializable {

    private ExerciceImpl exerciceManager = new ExerciceImpl();

    @FXML    private AnchorPane rootPane;
    @FXML    private Pane contentPane;
    @FXML    private ComboBox<Exercice> cmbListExercices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void listExercices(int idLevel) {
        List<Exercice> exerciceList = exerciceManager.getAllExercicesByID(idLevel);
        ObservableList<Exercice> exerciceMenu = FXCollections.observableArrayList();
        exerciceMenu.addAll(exerciceList);
        cmbListExercices.setItems(exerciceMenu);

    }

    @FXML
    public void loadExercice(ActionEvent event) {

        try {
            int idExercice = cmbListExercices.getValue().getIdExercice();
            Exercice exerciceObj = exerciceManager.getExerciceByID(idExercice);
            uploadPane(exerciceObj);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void uploadPane(Exercice exerciceObj) throws IOException {

        contentPane.getChildren().clear();
        int idTypeExercice = exerciceObj.getIdTypeExercice().getIdTypeExercice();
        int idExercice = exerciceObj.getIdExercice();

        URL url = null;
        FXMLLoader loader = null;
        Pane tempPane;

        switch (idTypeExercice){
            case 1:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/translateOpenWindow.fxml").toURI().toURL();
                loader = new FXMLLoader(url);
                tempPane = loader.load();
                contentPane.getChildren().add(tempPane);
                TranslateOpenWindowController translateOpenWindowController = loader.<TranslateOpenWindowController>getController();
                translateOpenWindowController.setListen(false);
                translateOpenWindowController.showData(idExercice);
                break;
            case 2:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/translateSortWindow.fxml").toURI().toURL();
                loader = new FXMLLoader(url);
                tempPane = loader.load();
                contentPane.getChildren().add(tempPane);
                TranslateSortWindowController translateSortWindowController = loader.<TranslateSortWindowController>getController();
                translateSortWindowController.setListen(false);
                translateSortWindowController.showData(idExercice);
                break;
            case 3:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/translateSortWindow.fxml").toURI().toURL();
                loader = new FXMLLoader(url);
                tempPane = loader.load();
                contentPane.getChildren().add(tempPane);
                TranslateSortWindowController translateSortWindowController2 = loader.<TranslateSortWindowController>getController();
                translateSortWindowController2.setListen(true);
                translateSortWindowController2.showData(idExercice);
                break;
            case 4:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/translateOpenWindow.fxml").toURI().toURL();
                loader = new FXMLLoader(url);
                tempPane = loader.load();
                contentPane.getChildren().add(tempPane);
                TranslateOpenWindowController translateOpenWindowController2 = loader.<TranslateOpenWindowController>getController();
                translateOpenWindowController2.setListen(true);
                translateOpenWindowController2.showData(idExercice);
                break;
            case 5:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/wordMatchWindow.fxml").toURI().toURL();
                loader = new FXMLLoader(url);
                tempPane = loader.load();
                contentPane.getChildren().add(tempPane);
                WordMatchWindowController wordMatchWindowController = loader.<WordMatchWindowController>getController();
                wordMatchWindowController.showData(idExercice);
                break;
            case 6:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/wordFillWindow.fxml").toURI().toURL();
                loader = new FXMLLoader(url);
                tempPane = loader.load();
                contentPane.getChildren().add(tempPane);
                WordFillWindowController wordFillWindowController = loader.<WordFillWindowController>getController();
                wordFillWindowController.showData(idExercice);
                break;
            case 7:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/typeTestWindow.fxml").toURI().toURL();
                loader = new FXMLLoader(url);
                tempPane = loader.load();
                contentPane.getChildren().add(tempPane);
                TypeTestWindowController typeTestWindowController = loader.<TypeTestWindowController>getController();
                typeTestWindowController.showData(idExercice);
                break;
        }
    }

    @FXML
    void close(MouseEvent event){
        rootPane.getScene().getWindow().hide();
    }
}
