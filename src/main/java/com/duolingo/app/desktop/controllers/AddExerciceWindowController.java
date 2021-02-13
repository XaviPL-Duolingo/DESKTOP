package com.duolingo.app.desktop.controllers;

import com.duolingo.app.interfaces.impl.TypeExerciceImpl;
import com.duolingo.app.model.TypeExercice;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
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

    @FXML    private Pane rootPane;
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
    void checkTypeExercice(ActionEvent event) {

        int idTypeExercice = cmbTypeExercice.getSelectionModel().getSelectedItem().getIdTypeExercice();
        URL url = null;
        switch (idTypeExercice){
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }

        System.out.println(url.toString());

    }
    @FXML
    void close(MouseEvent event){
        rootPane.getScene().getWindow().hide();
    }
}
