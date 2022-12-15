package Controllers.Tutor;

import DBConnection.DBHandler;
import Model.CourseDetails;
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
public class Delete_CourseController implements Initializable {

    @FXML
    private TableView<CourseDetails> tableCourse;
    @FXML
    private TableColumn<CourseDetails, String> col_coursename;
    @FXML
    private TableColumn<CourseDetails, String> col_courseid;
    @FXML
    private TableColumn<CourseDetails, String> col_tutorname;
    @FXML
    private TableColumn<CourseDetails, String> col_assignedmarks;
    @FXML
    private TableColumn<CourseDetails, String> col_coursecontent;
    @FXML
    private TableColumn<CourseDetails, String> col_id;

    @FXML
    private TextField reg_txt_id;
    @FXML
    private TextField add_coursename;
    @FXML
    private TextField add_courseid;
    @FXML
    private TextField add_tutorname;
    @FXML
    private TextField add_coursecontent;
    @FXML
    private TextField add_assignmedmarks;
    @FXML
    private Button btn_delete_course;
    @FXML
    private Button btn_refersh;

    // Initialize observable list to database
    private ObservableList<CourseDetails> data;

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
                        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM add_course");

                        while (rs.next()) {
                            // get string from db
                            data.add(new CourseDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

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
        col_assignedmarks.setCellValueFactory(new PropertyValueFactory<>("assignedMarks"));
        col_coursecontent.setCellValueFactory(new PropertyValueFactory<>("courseContent"));

        tableCourse.setItems(null);
        tableCourse.setItems(data);

        new DBHandler();
        add_coursename.setDisable(true);
        reg_txt_id.setDisable(true);
        add_courseid.setDisable(true);
        add_tutorname.setDisable(true);
        add_coursecontent.setDisable(true);
        add_assignmedmarks.setDisable(true);

    }

    @FXML
    private void deleteCourseButtonAction(MouseEvent event) {
        //updateLeavedStudentDB();

        String delete = "DELETE from add_course where id = ?";
        String delete2 = "DELETE from add_assessment where id = ?";
        connection = DBHandler.connectDB();
        try {
            pst = connection.prepareStatement(delete);
            pst.setString(1, reg_txt_id.getText());
            reg_txt_id.getText();
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted Selected Course");
            clearFields();
            autoRefresh();

            pst = connection.prepareStatement(delete2);
            pst.setString(1, reg_txt_id.getText());
            reg_txt_id.getText();
            pst.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Deleted Selected Course");
            clearFields();
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
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM add_course");

            while (rs.next()) {
                // get string from db
                data.add(new CourseDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        // set cell values
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        col_courseid.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        col_tutorname.setCellValueFactory(new PropertyValueFactory<>("tutorName"));
        col_assignedmarks.setCellValueFactory(new PropertyValueFactory<>("assignedMarks"));
        col_coursecontent.setCellValueFactory(new PropertyValueFactory<>("courseContent"));

        tableCourse.setItems(null);
        tableCourse.setItems(data);
    }

    @FXML
    private void refreshButtionClickAction(MouseEvent event) {
        connection = DBHandler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM add_course");

            while (rs.next()) {
                // get string from db
                data.add(new CourseDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        // set cell values
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        col_courseid.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        col_tutorname.setCellValueFactory(new PropertyValueFactory<>("tutorName"));
        col_assignedmarks.setCellValueFactory(new PropertyValueFactory<>("assignedMarks"));
        col_coursecontent.setCellValueFactory(new PropertyValueFactory<>("courseContent"));

        tableCourse.setItems(null);
        tableCourse.setItems(data);
    }

    @FXML
    private void displaySelectedAction(MouseEvent event) {
        CourseDetails course = tableCourse.getSelectionModel().getSelectedItem();
        if (course == null) {
            JOptionPane.showMessageDialog(null, "Nothing Selected!");
        } else {
            String id = course.getId();
            String courseName = course.getcourseName();
            String courseID = course.getcourseID();
            String tutorName = course.gettutorName();
            String assignedMarks = course.getassignedMarks();
            String courseContent = course.getcourseContent();

            reg_txt_id.setText(id);
            add_coursename.setText(courseName);
            add_courseid.setText(courseID);
            add_tutorname.setText(tutorName);
            add_assignmedmarks.setText(assignedMarks);
            add_coursecontent.setText(courseContent);
        }
    }

    private void clearFields() {

        reg_txt_id.setText("");
        add_coursename.setText("");
        add_courseid.setText("");
        add_tutorname.setText("");
        add_assignmedmarks.setText("");
        add_coursecontent.setText("");
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
