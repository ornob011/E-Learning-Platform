package Controllers.Tutor;

import Model.CourseDetails;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Update_CourseController implements Initializable {

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
    private Button btn_update_course;

    @FXML
    private Button btn_refersh;
    // Initialize observable list to database
    private ObservableList<CourseDetails> data;

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

    /**
     * Initializes the controller class.
     */
    private Connection connection;
    private PreparedStatement pst;

    @FXML
    private TextField reg_txt_id;
    @FXML
    private TableColumn<CourseDetails, String> col_id;
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
    private void updateCourseButtonAction(MouseEvent event) {
        String id = reg_txt_id.getText();
        String courseName = add_coursename.getText();
        String courseID = add_courseid.getText();
        String tutorName = add_tutorname.getText();
        String assignedMarks = add_assignmedmarks.getText();
        String courseContent = add_coursecontent.getText();

        if (courseName.equals("")
                || courseName.equals("")
                || courseID.equals("")
                || tutorName.equals("")
                || assignedMarks.equals("")
                || courseContent.equals("")) {
            JOptionPane.showMessageDialog(null, "Some Fields are missing!");
        } else {
            String update = "UPDATE add_course set id = ?, courseName = ?,courseID = ?,tutorName = ?,assignedMarks = ?,courseContent = ? where id = '" + id + "' ";
            connection = DBHandler.connectDB();
            try {
                pst = connection.prepareStatement(update);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                pst.setString(1, reg_txt_id.getText());
                pst.setString(2, add_coursename.getText());
                pst.setString(3, add_courseid.getText());
                pst.setString(4, add_tutorname.getText());
                pst.setString(5, add_assignmedmarks.getText());
                pst.setString(6, add_coursecontent.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Successfully Updated Course!!");
                autoRefresh();
            } catch (SQLException ex) {
                Logger.getLogger(Add_CourseController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
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
