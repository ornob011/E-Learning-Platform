package Controllers.Parent;

import Controllers.Student.*;
import Model.CommunicationDetails;
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
public class CommunicationController implements Initializable {

    @FXML
    private TextField add_coursename;
    @FXML
    private TextField add_tutorname;
    @FXML
    private TextField add_fathername;
    @FXML
    private TextField add_parentmessage;
    @FXML
    private TextField add_studentName;
    @FXML
    private TextField reg_txt_id;

    // Initialize observable list to database
    private ObservableList<CommunicationDetails> data;

    @FXML
    private TableView<CommunicationDetails> tableAssessment;
    @FXML
    private TableColumn<CommunicationDetails, String> col_coursename;
    @FXML
    private TableColumn<CommunicationDetails, String> col_fathername;
    @FXML
    private TableColumn<CommunicationDetails, String> col_tutorname;
    @FXML
    private TableColumn<CommunicationDetails, String> col_studentname;
    @FXML
    private TableColumn<CommunicationDetails, String> col_parentmessage;
    @FXML
    private TableColumn<CommunicationDetails, String> col_id;

    /**
     * Initializes the controller class.
     */
    private Connection connection;
    private PreparedStatement pst;

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
                        ResultSet rs = connection.createStatement().executeQuery("SELECT parent_student.id, parent_student.fatherName, parent_student.studentName, parent_student.tutorName, enroll_course.courseName FROM parent_student INNER JOIN enroll_course ON enroll_course.studentName = parent_student.studentName");

                        while (rs.next()) {
                            // get string from db
                            data.add(new CommunicationDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(5)));

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
        col_studentname.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        col_tutorname.setCellValueFactory(new PropertyValueFactory<>("tutorName"));
        col_fathername.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
//        col_parentmessage.setCellValueFactory(new PropertyValueFactory<>("parentMessage"));

        tableAssessment.setItems(null);
        tableAssessment.setItems(data);

        add_coursename.setDisable(true);
        reg_txt_id.setDisable(true);
        add_studentName.setDisable(true);
        add_tutorname.setDisable(true);
        add_fathername.setDisable(true);

        new DBHandler();
    }

    private void autoRefresh() {
        connection = DBHandler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM communication");

            while (rs.next()) {
                // get string from db
                data.add(new CommunicationDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        // set cell values
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_coursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        col_studentname.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        col_tutorname.setCellValueFactory(new PropertyValueFactory<>("tutorName"));
        col_fathername.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
        col_parentmessage.setCellValueFactory(new PropertyValueFactory<>("parentMessage"));

        tableAssessment.setItems(null);
        tableAssessment.setItems(data);

        add_coursename.setDisable(true);
        reg_txt_id.setDisable(true);
        add_studentName.setDisable(true);
        add_tutorname.setDisable(true);
        add_fathername.setDisable(true);

    }

    @FXML
    private void sendmessageButtonAction(MouseEvent event) {
        String id = reg_txt_id.getText();
        String courseName = add_coursename.getText();
        String tutorName = add_tutorname.getText();
        String studentName = add_studentName.getText();
        String fatherName = add_fathername.getText();
        String parentMessage = add_parentmessage.getText();

        if (courseName.equals("")
                || tutorName.equals("")
                || studentName.equals("")
                || fatherName.equals("")
                || parentMessage.equals("")) {
            JOptionPane.showMessageDialog(null, "Some Fields are missing!");
        } else {
            String update = "INSERT INTO communication(id, courseName,tutorName,studentName,fatherName,parentMessage)" + "VALUES(?,?,?,?,?,?)";
            connection = DBHandler.connectDB();
            try {
                pst = connection.prepareStatement(update);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {

                pst.setString(1, reg_txt_id.getText());
                pst.setString(2, add_coursename.getText());
                pst.setString(3, add_tutorname.getText());
                pst.setString(4, add_studentName.getText());
                pst.setString(5, add_fathername.getText());
                pst.setString(6, add_parentmessage.getText());
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Message has been Sent!!");
                autoRefresh();
            } catch (SQLException ex) {
                Logger.getLogger(View_Enrolled_CourseController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    @FXML
    private void displaySelectedAction(MouseEvent event) {
        CommunicationDetails course = tableAssessment.getSelectionModel().getSelectedItem();
        if (course == null) {
            JOptionPane.showMessageDialog(null, "Nothing Selected!");
        } else {
            String id = course.getId();
            String courseName = course.getcourseName();
            String studentName = course.getstudentName();
            String tutorName = course.gettutorName();
            String fatherName = course.getfatherName();
            String parentMessage = course.getparentMessage();

            reg_txt_id.setText(id);
            add_coursename.setText(courseName);
            add_studentName.setText(studentName);
            add_tutorname.setText(tutorName);
            add_fathername.setText(fatherName);
            add_parentmessage.setText(parentMessage);
        }
    }

    @FXML
    private void back_btn_clicked(MouseEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();

        Stage stu_Menu = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Parent/Parent_Menu.fxml"));
        Scene scene = new Scene(root);
        stu_Menu.setScene(scene);
        stu_Menu.initStyle(StageStyle.TRANSPARENT);
        stu_Menu.show();
        stu_Menu.setResizable(false);

    }
}
