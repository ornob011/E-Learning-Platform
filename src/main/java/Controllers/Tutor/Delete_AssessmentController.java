package Controllers.Tutor;

import DBConnection.DBHandler;
import Model.AssessmentDetails;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author
 */
public class Delete_AssessmentController implements Initializable {

    @FXML
    private TableView<AssessmentDetails> tableAssessment;
    @FXML
    private TableColumn<AssessmentDetails, String> col_coursename;
    @FXML
    private TableColumn<AssessmentDetails, String> col_courseid;
    @FXML
    private TableColumn<AssessmentDetails, String> col_tutorname;
    @FXML
    private TableColumn<AssessmentDetails, String> col_givenMarks;
    @FXML
    private TableColumn<AssessmentDetails, String> col_question;
    @FXML
    private TableColumn<AssessmentDetails, String> col_id;

    @FXML
    private TextField reg_txt_id;
    @FXML
    private TextField add_coursename;
    @FXML
    private TextField add_courseid;
    @FXML
    private TextField add_tutorname;
    @FXML
    private TextField add_question;
    @FXML
    private TextField add_givenMarks;
    @FXML
    private Button btn_delete_course;
    @FXML
    private Button btn_refersh;

    // Initialize observable list to database
    private ObservableList<AssessmentDetails> data;

    /**
     * Initializes the controller class.
     */
    private Connection connection;
    private PreparedStatement pst;

    @FXML
    private Button btn_back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
                        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM add_assessment");

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
        col_givenMarks.setCellValueFactory(new PropertyValueFactory<>("givenMarks"));
        col_question.setCellValueFactory(new PropertyValueFactory<>("question"));

        tableAssessment.setItems(null);
        tableAssessment.setItems(data);

        new DBHandler();
        add_coursename.setDisable(true);
        reg_txt_id.setDisable(true);
        add_courseid.setDisable(true);
        add_tutorname.setDisable(true);
        add_question.setDisable(true);
        add_givenMarks.setDisable(true);

    }

    @FXML
    private void deleteAssessmentButtonAction(MouseEvent event) {
        //updateLeavedStudentDB();

        String delete = "DELETE from add_assessment where id = ?";
        connection = DBHandler.connectDB();
        try {
            pst = connection.prepareStatement(delete);
            pst.setString(1, reg_txt_id.getText());
            reg_txt_id.getText();
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted Selected Assessment");
            clearFields();
            autoRefresh();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void updateLeavedStudentDB() {
        String query = "INSERT INTO deleted_course (id,courseName,courseID,tutorName,givenMarks,question) VALUES(?,?,?,?,?,?)";
        connection = DBHandler.connectDB();
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, reg_txt_id.getText());
            pst.setString(2, add_coursename.getText());
            pst.setString(3, add_courseid.getText());
            pst.setString(4, add_tutorname.getText());
            pst.setString(5, add_question.getText());
            pst.setString(6, add_givenMarks.getText());

            pst.executeUpdate();

            autoRefresh();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void autoRefresh() {
        connection = DBHandler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM add_assessment");

            while (rs.next()) {
                // get string from db
                data.add(new AssessmentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        // set cell values
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        col_courseid.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        col_tutorname.setCellValueFactory(new PropertyValueFactory<>("tutorName"));
        col_givenMarks.setCellValueFactory(new PropertyValueFactory<>("givenMarks"));
        col_question.setCellValueFactory(new PropertyValueFactory<>("question"));

        tableAssessment.setItems(null);
        tableAssessment.setItems(data);
    }

    @FXML
    private void refreshButtionClickAction(MouseEvent event) {
        connection = DBHandler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM add_assessment");

            while (rs.next()) {
                // get string from db
                data.add(new AssessmentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        // set cell values
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        col_courseid.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        col_tutorname.setCellValueFactory(new PropertyValueFactory<>("tutorName"));
        col_givenMarks.setCellValueFactory(new PropertyValueFactory<>("givenMarks"));
        col_question.setCellValueFactory(new PropertyValueFactory<>("question"));

        tableAssessment.setItems(null);
        tableAssessment.setItems(data);
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
            String givenMarks = course.getgivenMarks();
            String question = course.getquestion();

            reg_txt_id.setText(id);
            add_coursename.setText(courseName);
            add_courseid.setText(courseID);
            add_tutorname.setText(tutorName);
            add_givenMarks.setText(givenMarks);
            add_question.setText(question);
        }
    }

    private void clearFields() {

        reg_txt_id.setText("");
        add_coursename.setText("");
        add_courseid.setText("");
        add_tutorname.setText("");
        add_givenMarks.setText("");
        add_question.setText("");
    }

    @FXML
    private void back_btn_clicked(MouseEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();

        Stage stu_Menu = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Tutor/Tutor_Menu.fxml"));
        Scene scene = new Scene(root);
        stu_Menu.setScene(scene);
        stu_Menu.initStyle(StageStyle.TRANSPARENT);
        stu_Menu.show();
        stu_Menu.setResizable(false);
    }
}
