
import com.duolingo.app.interfaces.impl.*;
import com.duolingo.app.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private LanguageImpl languageManager = new LanguageImpl();
    private CourseImpl courseManager = new CourseImpl();
    private CategoryImpl categoryManager = new CategoryImpl();
    private LevelImpl levelManager = new LevelImpl();


    private double x, y;

    @FXML
    private ListView<Course> listCourses;

    @FXML
    private ListView<Category> listCategories;

    @FXML
    private ListView<Level> listLevels;

    @FXML
    private Button btnCreateCourse;

    @FXML
    private Button btnCreateCategory;

    @FXML
    private Button btnCreateLevel;

    @FXML
    private Button btnApplyFilter;

    @FXML
    public ComboBox<Language> cmbDestLanguage;

    @FXML
    private ComboBox<Language> cmbOriginLanguage;

    @FXML
    void windowDrag(MouseEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);

    }

    @FXML
    void windowPressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    @FXML
    void minimize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // PREVIOS

        btnCreateCourse.setDisable(true);
        btnCreateCategory.setDisable(true);
        btnCreateLevel.setDisable(true);

        // COMBOBOXES Y FILTRO DE CURSOS

        List<Language> languageList = languageManager.getAllLanguages();
        ObservableList<Language> languageMenu = FXCollections.observableArrayList();

        languageMenu.add(new Language(0, "[TODOS LOS IDIOMAS]", "ALL"));
        languageMenu.addAll(languageList);

        cmbOriginLanguage.setItems(languageMenu);
        cmbOriginLanguage.setValue(languageMenu.get(0));

        cmbDestLanguage.setItems(languageMenu);
        cmbDestLanguage.setValue(languageMenu.get(0));


    }

    @FXML
    void checkCourses(){

        listCourses.getItems().clear();
        listCategories.getItems().clear();
        listLevels.getItems().clear();


        int idOriginLang = cmbOriginLanguage.getValue().getIdLanguage();
        int idDestLang = cmbDestLanguage.getValue().getIdLanguage();

        System.out.println(idOriginLang + "//" +  idDestLang);

        if (idOriginLang != idDestLang){
            applyFilter(idOriginLang, idDestLang);
        }else{
            if (idOriginLang == 0 && idDestLang == 0){
                applyFilter(idOriginLang, idDestLang);
            }else{
                System.out.println("Filtro no válido...");
            }
        }

    }

    private void applyFilter(int idOriginLang, int idDestLang){

        listCourses.getItems().clear();
        listCategories.getItems().clear();
        listLevels.getItems().clear();

        List<Course> courseList = courseManager.getAllCoursesByID(idOriginLang, idDestLang);
        ObservableList<Course> courseMenu = FXCollections.observableArrayList();
        courseMenu.addAll(courseList);
        listCourses.setItems(courseMenu);

        if (courseList.size() != 0){
            btnCreateCourse.setDisable(true);
        }else {
            btnCreateCourse.setDisable(false);
            btnCreateCategory.setDisable(true);
            btnCreateLevel.setDisable(true);
        }
    }

    @FXML
    void createCourse(){

        int idOriginLang = cmbOriginLanguage.getValue().getIdLanguage();
        int idDestLang = cmbDestLanguage.getValue().getIdLanguage();
        courseManager.insertCourse(idOriginLang, idDestLang);
        applyFilter(idOriginLang, idDestLang);

    }

    @FXML
    void checkCategories(){

        listCategories.getItems().clear();
        listLevels.getItems().clear();

        try {
            int idCourse = listCourses.getSelectionModel().getSelectedItem().getIdCourse();
            List<Category> categoryList = categoryManager.getAllCategoriesByID(idCourse);
            ObservableList<Category> categoryMenu = FXCollections.observableArrayList();
            categoryMenu.addAll(categoryList);
            listCategories.setItems(categoryMenu);
            btnCreateCategory.setDisable(false);
        }catch (Exception e){
            System.out.println("[DEBUG] - No hay CURSO seleccionado...");
        }

    }
    @FXML
    void createCategory(){

        int idCourse = listCourses.getSelectionModel().getSelectedItem().getIdCourse();
        String nameCourse = JOptionPane.showInputDialog("Nombre de la categoría?");
        categoryManager.insertCategory(idCourse, nameCourse);
        checkCategories();

    }

    @FXML
    void checkLevels(){

        listLevels.getItems().clear();

        try {
            int idCategory = listCategories.getSelectionModel().getSelectedItem().getIdCategory();
            List<Level> levelList = levelManager.getAllLevelsByID(idCategory);
            ObservableList<Level> levelMenu = FXCollections.observableArrayList();
            levelMenu.addAll(levelList);
            listLevels.setItems(levelMenu);
            btnCreateLevel.setDisable(false);
        }catch (Exception e){
            System.out.println("[DEBUG] - No hay CATEGORIA seleccionada...");
        }

    }

    @FXML
    void createLevel(){

        int idCategory = listCategories.getSelectionModel().getSelectedItem().getIdCategory();
        String codeLevel = JOptionPane.showInputDialog("Identificador del nivel?");
        levelManager.insertLevel(idCategory, codeLevel);
        checkLevels();

    }

    @FXML
    void checkExercices(){

        try{
            int idLevel = listLevels.getSelectionModel().getSelectedItem().getIdLevel();
        }catch (Exception e){
            System.out.println("[DEBUG] - No hay LEVEL seleccionado...");
        }

    }

}
