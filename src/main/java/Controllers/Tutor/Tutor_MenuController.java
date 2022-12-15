package Controllers.Tutor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author 
 */
public class Tutor_MenuController implements Initializable {
    
    double xoffset, yoffset;

    @FXML
    private Button addNewCourse;
    @FXML
    private Button updateCourse;
    @FXML
    private Button deleteCourse;
    @FXML
    private Button addAssessment;
    @FXML
    private Button updateAssessment;
    @FXML
    private Button deleteAssessment;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addNewCourseAction(MouseEvent event) throws IOException {
        addNewCourse.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Tutor/Add_Course.fxml"));
        Scene scene = new Scene(root);        
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void updateCourseAction(MouseEvent event) throws IOException {
        updateCourse.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Tutor/Update_Course.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void deleteCourseAction(MouseEvent event) throws IOException {
        deleteCourse.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Tutor/Delete_Course.fxml"));
        Scene scene = new Scene(root);        
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
    @FXML
    private void addAssessmentAction(MouseEvent event) throws IOException {
        addAssessment.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Tutor/Add_Assessment.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
    @FXML
    private void updateAssessmentAction(MouseEvent event) throws IOException {
        updateAssessment.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Tutor/Update_Assessment.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
    @FXML
    private void deleteAssessmentAction(MouseEvent event) throws IOException {
        deleteAssessment.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Tutor/Delete_Assessment.fxml"));
        Scene scene = new Scene(root);        
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void back_btn_clicked(MouseEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/MenuComponent.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                xoffset = event.getSceneX();
                yoffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                login.setX(event.getScreenX() - xoffset);
                login.setY(event.getScreenY() - yoffset);
            }
        });
    }
}
