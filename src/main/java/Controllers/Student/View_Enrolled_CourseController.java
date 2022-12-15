package Controllers.Student;

import Model.EnrollmentDetails;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import DBConnection.DBHandler;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author
 */
public class View_Enrolled_CourseController implements Initializable {

    @FXML
    private TextField add_coursename;
    @FXML
    private TextField add_courseid;
    @FXML
    private TextField add_tutorname;
    @FXML
    private TextField add_studentName;
    @FXML
    private TextField add_reg;
    // Initialize observable list to database
    private ObservableList<EnrollmentDetails> data;

    @FXML
    private TableView<EnrollmentDetails> tableCourse;
    @FXML
    private TableColumn<EnrollmentDetails, String> col_coursename;
    @FXML
    private TableColumn<EnrollmentDetails, String> col_courseid;
    @FXML
    private TableColumn<EnrollmentDetails, String> col_tutorname;
    @FXML
    private TableColumn<EnrollmentDetails, String> col_studentName;
    @FXML
    private TableColumn<EnrollmentDetails, String> col_reg;

    /**
     * Initializes the controller class.
     */
    private Connection connection;
    private PreparedStatement pst;

    @FXML
    private TextField reg_txt_id;
    @FXML
    private TableColumn<EnrollmentDetails, String> col_id;
    @FXML
    private Button btn_back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        final JOptionPane optionPane = new JOptionPane("Please Wait...Hosted SQL Server is Slow", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

        final JDialog dialog = new JDialog();

        dialog.setLocationByPlatform(true);
        dialog.setTitle("Slow server");
        dialog.setModal(true);

        dialog.setContentPane(optionPane);

        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connection = DBHandler.connectDB();
                    data = FXCollections.observableArrayList();

                    try {
                        // Execure query
                        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM enroll_course");

                        while (rs.next()) {
                            // get string from db
                            data.add(new EnrollmentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.setVisible(false);
            }
        }).start();
        dialog.setVisible(true);

        // set cell values
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        col_courseid.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        col_tutorname.setCellValueFactory(new PropertyValueFactory<>("tutorName"));
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        col_reg.setCellValueFactory(new PropertyValueFactory<>("regNo"));

        tableCourse.setItems(null);
        tableCourse.setItems(data);

        add_coursename.setDisable(true);
        reg_txt_id.setDisable(true);
        add_courseid.setDisable(true);
        add_tutorname.setDisable(true);
        add_studentName.setDisable(true);
        add_reg.setDisable(true);

        new DBHandler();
    }

    private void autoRefresh() {
        connection = DBHandler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM enroll_course");

            while (rs.next()) {
                // get string from db
                data.add(new EnrollmentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        // set cell values
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        col_courseid.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        col_tutorname.setCellValueFactory(new PropertyValueFactory<>("tutorName"));
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        col_reg.setCellValueFactory(new PropertyValueFactory<>("regNo"));

        tableCourse.setItems(null);
        tableCourse.setItems(data);

        add_coursename.setDisable(true);
        reg_txt_id.setDisable(true);
        add_courseid.setDisable(true);
        add_tutorname.setDisable(true);
        add_studentName.setDisable(true);
        add_reg.setDisable(true);
    }

    @FXML
    private void displaySelectedAction(MouseEvent event) {
        EnrollmentDetails course = tableCourse.getSelectionModel().getSelectedItem();
        if (course == null) {
            JOptionPane.showMessageDialog(null, "Nothing Selected!");
        } else {
            String id = course.getId();
            String courseName = course.getcourseName();
            String courseID = course.getcourseID();
            String tutorName = course.gettutorName();
            String studentName = course.getstudentName();
            String regNo = course.getregNo();

            reg_txt_id.setText(id);
            add_coursename.setText(courseName);
            add_courseid.setText(courseID);
            add_tutorname.setText(tutorName);
            add_studentName.setText(studentName);
            add_reg.setText(studentName);
        }
    }

    @FXML
    private void back_btn_clicked(MouseEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();

        Stage stu_Menu = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/Student_Menu.fxml"));
        Scene scene = new Scene(root);
        stu_Menu.setScene(scene);
        stu_Menu.initStyle(StageStyle.TRANSPARENT);
        stu_Menu.show();
        stu_Menu.setResizable(false);

    }
}
