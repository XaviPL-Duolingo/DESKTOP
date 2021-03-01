package com.duolingo.app.desktop.controllers;

import com.duolingo.app.interfaces.impl.ItemImpl;
import com.duolingo.app.model.Item;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemsWindowController implements Initializable {

    ItemImpl itemManager = new ItemImpl();

    @FXML    private AnchorPane rootPane;
    @FXML    private TextField txtNameItem, txtPriceItem,txtDescItem;
    @FXML    private Button btnAddItem, btnRemoveItem;
    @FXML    private ListView<Item> listItems;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reloadList();
    }


    private void reloadList(){
        listItems.getItems().removeAll();
        listItems.getItems().addAll(itemManager.getAllItems());
    }

    @FXML
    void addItem(){

        String nameItem = txtNameItem.getText();
        String descItem = txtDescItem.getText();
        int priceItem = Integer.parseInt(txtPriceItem.getText());

        if (!nameItem.isEmpty()){
            if (!descItem.isEmpty()){
                if (!txtPriceItem.getText().isEmpty()){
                    try{
                        itemManager.insertItem(nameItem, descItem, priceItem);
                        System.out.println("[DEBUG] - ITEM creado correctamente!");
                        clear();
                        reloadList();
                    }catch (Exception e){
                        System.out.println("[DEBUG] - Error al crear ITEM...");
                    }
                }else {
                    System.out.println("[DEBUG] - Falta añadir priceItem o este no es valido // Debe tener un valor aunque sea 0");
                }
            }else {
                System.out.println("[DEBUG] - Falta añadir descItem o este no es valido...");
            }
        }else {
            System.out.println("[DEBUG] - Falta añadir nameItem o este no es valido...");
        }

    }

    @FXML
    void showItem(){

        try {
            Item itemObj = listItems.getSelectionModel().getSelectedItem();
            txtNameItem.setText(itemObj.getNameItem());
            txtPriceItem.setText(Integer.toString(itemObj.getPriceItem()));
            txtDescItem.setText(itemObj.getDescription());
        }catch (Exception e){
            System.out.println("[DEBUG] - No hay ITEM seleccionado...");
        }

    }

    private void clear(){

        txtNameItem.clear();
        txtDescItem.clear();
        txtPriceItem.clear();
    }

    @FXML
    void removeItem(){

        try {
            int idItem = listItems.getSelectionModel().getSelectedItem().getIdItem();
            itemManager.removeItem(idItem);
            reloadList();
            clear();
            System.out.println("[DEBUG] - ITEM ["+idItem+"] eliminado correctamente!");
        }catch (Exception e){
            System.out.println("[DEBUG] - Error al eliminar ITEM...");
        }

    }

    @FXML
    void close(MouseEvent event){
        rootPane.getScene().getWindow().hide();
    }
}
