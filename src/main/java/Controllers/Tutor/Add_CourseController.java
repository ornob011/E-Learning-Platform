package Controllers.Tutor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

import DBConnection.DBHandler;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author 
 */
public class Add_CourseController implements Initializable {

    @FXML
    private TextField add_coursename;
    @FXML
    private TextField add_courseid;
    @FXML
    private TextField add_tutorname;
    @FXML
    private TextField add_assignmedmarks;
    @FXML
    private TextField add_coursecontent;


    /**
     * Initializes the controller class.
     */
    private Connection connection;
    private PreparedStatement pst;
    private PreparedStatement pst2;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_add_course;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new DBHandler();
    }

    @FXML
    private void addCourseButtonAction(MouseEvent event) {
        String courseName = add_coursename.getText();
        String courseID = add_courseid.getText();
        String tutorName = add_tutorname.getText();
        String assignedMarks = add_assignmedmarks.getText();
        String courseContent = add_coursecontent.getText();
        
        if (courseName.equals("")
                || courseID.equals("")
                || tutorName.equals("")
                || assignedMarks.equals("")
                || courseContent.equals("")) 
        {
            JOptionPane.showMessageDialog(null, "All Fields Are Required!");
            setTExtRefresh();
        } else {
            String insert = "INSERT INTO add_course(courseName,courseID,tutorName,assignedMarks,courseContent)" + "VALUES(?,?,?,?,?)";
            String insert2 = "INSERT INTO add_assessment(courseName,courseID,tutorName)" + "VALUES(?,?,?)";
            connection = DBHandler.connectDB();
            try {
                pst = connection.prepareStatement(insert);
                pst2 = connection.prepareStatement(insert2);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                pst.setString(1, add_coursename.getText());
                pst.setString(2, add_courseid.getText());
                pst.setString(3, add_tutorname.getText());
                pst.setString(4, add_assignmedmarks.getText());
                pst.setString(5, add_coursecontent.getText());

                pst.executeUpdate();
                
                pst2.setString(1, add_coursename.getText());
                pst2.setString(2, add_courseid.getText());
                pst2.setString(3, add_tutorname.getText());
                pst2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Registered!");
                setTExtRefresh();
            } catch (SQLException ex) {
                Logger.getLogger(Add_CourseController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    @FXML
    private void back_btn_clicked(MouseEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();

        Stage stu_Menu = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Tutor/Tutor_Menu.fxml"));
        Scene scene = new Scene(root);
        stu_Menu.initStyle(StageStyle.TRANSPARENT);
        stu_Menu.setScene(scene);
        stu_Menu.show();
        stu_Menu.setResizable(false);
    }

    @FXML
    private void setTExtRefresh() {
        add_coursename.setText("");
        add_courseid.setText("");
        add_tutorname.setText("");
        add_assignmedmarks.setText("");
        add_coursecontent.setText("");
    }
}
