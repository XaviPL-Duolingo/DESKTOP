package com.duolingo.app.desktop.controllers;

import com.duolingo.app.desktop.controllers.typeExercices.TypeTestWindowController;
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
import java.net.MalformedURLException;
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
        switch (idTypeExercice){
            case 1:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/translateOpenWindow.fxml").toURI().toURL();
                break;
            case 2:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/translateSortWindow.fxml").toURI().toURL();
                break;
            case 3:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/translateSortWindow.fxml").toURI().toURL();
                break;
            case 4:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/translateOpenWindow.fxml").toURI().toURL();
                break;
            case 5:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/wordMatchWindow.fxml").toURI().toURL();
                break;
            case 6:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/wordFillWindow.fxml").toURI().toURL();
                break;
            case 7:
                url = new File("src/main/java/com/duolingo/app/desktop/windows/typeExercices/typeTestWindow.fxml").toURI().toURL();
                /*loader = new FXMLLoader(url);
                Pane tempPane = loader.load();
                TypeTestWindowController typeTestWindowController = loader.<TypeTestWindowController>getController();
                typeTestWindowController.setIdLevel(idLevel);
                typeTestWindowController.setIdTypeExercice(idTypeExercice);
                contentPane.getChildren().add(tempPane);*/
                break;
        }

        loader = new FXMLLoader(url);
        Pane tempPane = loader.load();
        contentPane.getChildren().add(tempPane);

    }

    public void setIdLevel(int idLevel){
        this.idLevel = idLevel;
    }

    @FXML
    void close(MouseEvent event){
        rootPane.getScene().getWindow().hide();
    }
}
