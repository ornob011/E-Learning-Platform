package Controllers.Student;

import Model.AssessmentDetails;
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
public class View_ResultController implements Initializable {

    @FXML
    private TextField add_coursename;
    @FXML
    private TextField add_courseid;
    @FXML
    private TextField add_tutorname;
    @FXML
    private TextField add_question;
    @FXML
    private TextField add_givenmarks;
    // Initialize observable list to database
    private ObservableList<AssessmentDetails> data;

    @FXML
    private TableView<AssessmentDetails> tableAssessment;
    @FXML
    private TableColumn<AssessmentDetails, String> col_coursename;
    @FXML
    private TableColumn<AssessmentDetails, String> col_courseid;
    @FXML
    private TableColumn<AssessmentDetails, String> col_tutorname;
    @FXML
    private TableColumn<AssessmentDetails, String> col_givenmarks;
    @FXML
    private TableColumn<AssessmentDetails, String> col_question;

    /**
     * Initializes the controller class.
     */
    private Connection connection;
    private PreparedStatement pst;

    @FXML
    private TextField reg_txt_id;
    @FXML
    private TableColumn<AssessmentDetails, String> col_id;
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
                        ResultSet rs = connection.createStatement().executeQuery("SELECT a.* FROM add_assessment a INNER JOIN enroll_course i on a.courseName = i.courseName");

                        while (rs.next()) {
                            // get string from db
                            data.add(new AssessmentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

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
        col_givenmarks.setCellValueFactory(new PropertyValueFactory<>("givenMarks"));
//        col_question.setCellValueFactory(new PropertyValueFactory<>("question"));

        tableAssessment.setItems(null);
        tableAssessment.setItems(data);

        add_coursename.setDisable(true);
        reg_txt_id.setDisable(true);
        add_courseid.setDisable(true);
        add_tutorname.setDisable(true);
        add_givenmarks.setDisable(true);
        add_question.setDisable(true);

        new DBHandler();
    }

    @FXML
    private void displaySelectedAction(MouseEvent event) {
        AssessmentDetails course = tableAssessment.getSelectionModel().getSelectedItem();
        if (course == null) {
            JOptionPane.showMessageDialog(null, "Nothing Selected!");
        } else {
            String id = course.getId();
            String courseName = course.getcourseName();
            String courseID = course.getcourseID();
            String tutorName = course.gettutorName();
            String question = course.getquestion();
            String givenMarks = course.getgivenMarks();

            reg_txt_id.setText(id);
            add_coursename.setText(courseName);
            add_courseid.setText(courseID);
            add_tutorname.setText(tutorName);
            add_givenmarks.setText(givenMarks);
            add_question.setText(question);
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
