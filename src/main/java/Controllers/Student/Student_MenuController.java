package Controllers.Student;

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
public class Student_MenuController implements Initializable {
    
    double xoffset, yoffset;

    @FXML
    private Button enrollCourse;
    @FXML
    private Button viewEnrolledCourse;
    @FXML
    private Button viewAssessment;
    @FXML
    private Button addAnswer;
    @FXML
    private Button makePayment;
    @FXML
    private Button viewResult;
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
    private void enrollCourseAction(MouseEvent event) throws IOException {
        enrollCourse.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/Enroll_Course.fxml"));
        Scene scene = new Scene(root);        
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void viewEnrolledCourseAction(MouseEvent event) throws IOException {
        viewEnrolledCourse.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/View_Enrolled_Course.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void viewAssessmentAction(MouseEvent event) throws IOException {
        viewAssessment.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/View_Assessment.fxml"));
        Scene scene = new Scene(root);        
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
    @FXML
    private void addAnswerAction(MouseEvent event) throws IOException {
        addAnswer.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/Answer.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
    @FXML
    private void makePaymentAction(MouseEvent event) throws IOException {
        makePayment.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/Make_Payment.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
    @FXML
    private void viewResultAction(MouseEvent event) throws IOException {
        viewResult.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/View_Result.fxml"));
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
