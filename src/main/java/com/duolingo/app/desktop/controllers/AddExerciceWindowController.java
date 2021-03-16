package com.duolingo.app.desktop.controllers;

import com.duolingo.app.desktop.controllers.typeExercices.*;
import com.duolingo.app.interfaces.impl.TypeExerciceImpl;
import com.duolingo.app.model.TypeExercice;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddExerciceWindowController implements Initializable {

    private TypeExerciceImpl typeExerciceManager = new TypeExerciceImpl();
    private int idLevel;

    @FXML    private Pane rootPane, contentPane;
    @FXML    private FontAwesomeIcon btnClose;
    @FXML    private ComboBox<TypeExercice> cmbTypeExercice;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<TypeExercice> typeExerciceList = typeExerciceManager.getAllTypesExercice();
        ObservableList<TypeExercice> typeExercicesMenu = FXCollections.observableArrayList();
        typeExercicesMenu.addAll(typeExerciceList);
        cmbTypeExercice.setItems(typeExercicesMenu);

    }

    @FXML
    void checkTypeExercice(ActionEvent event) throws IOException {

        contentPane.getChildren().clear();
        int idTypeExercice = cmbTypeExercice.getSelectionModel().getSelectedItem().getIdTypeExercice();

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
                translateOpenWindowController.setIdLevel(idLevel);
                translateOpenWindowController.setListen(false);
                break;
            case 2:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/translateSortWindow.fxml").toURI().toURL();
                loader = new FXMLLoader(url);
                tempPane = loader.load();
                contentPane.getChildren().add(tempPane);
                TranslateSortWindowController translateSortWindowController = loader.<TranslateSortWindowController>getController();
                translateSortWindowController.setIdLevel(idLevel);
                translateSortWindowController.setListen(false);
                break;
            case 3:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/translateSortWindow.fxml").toURI().toURL();
                loader = new FXMLLoader(url);
                tempPane = loader.load();
                contentPane.getChildren().add(tempPane);
                TranslateSortWindowController translateSortWindowController2 = loader.<TranslateSortWindowController>getController();
                translateSortWindowController2.setIdLevel(idLevel);
                translateSortWindowController2.setListen(true);
                break;
            case 4:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/translateOpenWindow.fxml").toURI().toURL();
                loader = new FXMLLoader(url);
                tempPane = loader.load();
                contentPane.getChildren().add(tempPane);
                TranslateOpenWindowController translateOpenWindowController2 = loader.<TranslateOpenWindowController>getController();
                translateOpenWindowController2.setIdLevel(idLevel);
                translateOpenWindowController2.setListen(true);
                break;
            case 5:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/wordMatchWindow.fxml").toURI().toURL();
                loader = new FXMLLoader(url);
                tempPane = loader.load();
                contentPane.getChildren().add(tempPane);
                WordMatchWindowController wordMatchWindowController = loader.<WordMatchWindowController>getController();
                wordMatchWindowController.setIdLevel(idLevel);
                break;
            case 6:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/wordFillWindow.fxml").toURI().toURL();
                loader = new FXMLLoader(url);
                tempPane = loader.load();
                contentPane.getChildren().add(tempPane);
                WordFillWindowController wordFillWindowController = loader.<WordFillWindowController>getController();
                wordFillWindowController.setIdLevel(idLevel);
                break;
            case 7:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/typeTestWindow.fxml").toURI().toURL();
                loader = new FXMLLoader(url);
                tempPane = loader.load();
                contentPane.getChildren().add(tempPane);
                TypeTestWindowController typeTestWindowController = loader.<TypeTestWindowController>getController();
                typeTestWindowController.setIdLevel(idLevel);
                break;
        }
    }

    public void setIdLevel(int idLevel){
        this.idLevel = idLevel;
    }

    @FXML
    void close(MouseEvent event){
        rootPane.getScene().getWindow().hide();
    }
}
