package Controllers.Admin;

import Controllers.Student.*;
import Model.ScheduleManage;
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
import javax.swing.JOptionPane;
import javax.swing.JDialog;

/**
 * FXML Controller class
 *
 * @author
 */
public class ScheduleController implements Initializable {

    @FXML
    private TextField add_tutorname;
    @FXML
    private TextField add_studentName;
    @FXML
    private TextField add_time;
    @FXML
    private TextField add_fathername;
    @FXML
    private Button btn_arrange;
    // Initialize observable list to database
    private ObservableList<ScheduleManage> data;

    @FXML
    private TableView<ScheduleManage> tableCourse;
    @FXML
    private TableColumn<ScheduleManage, String> col_tutorname;
    @FXML
    private TableColumn<ScheduleManage, String> col_fathername;
    @FXML
    private TableColumn<ScheduleManage, String> col_studentname;
    @FXML
    private TableColumn<ScheduleManage, String> col_schedule;

    /**
     * Initializes the controller class.
     */
    private Connection connection;
    private PreparedStatement pst;

    @FXML
    private TextField reg_txt_id;
    @FXML
    private TableColumn<ScheduleManage, String> col_id;
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
                        ResultSet rs = connection.createStatement().executeQuery("SELECT * from parent_student");

                        while (rs.next()) {
                            // get string from db
                            data.add(new ScheduleManage(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

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
        col_tutorname.setCellValueFactory(new PropertyValueFactory<>("tutorName"));
        col_fathername.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
        col_schedule.setCellValueFactory(new PropertyValueFactory<>("time"));
        col_studentname.setCellValueFactory(new PropertyValueFactory<>("studentName"));

        tableCourse.setItems(null);
        tableCourse.setItems(data);

        new DBHandler();
    }

    private void autoRefresh() {

        connection = DBHandler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * from parent_student");

            while (rs.next()) {
                // get string from db
                data.add(new ScheduleManage(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        // set cell values
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_tutorname.setCellValueFactory(new PropertyValueFactory<>("tutorName"));
        col_fathername.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
        col_schedule.setCellValueFactory(new PropertyValueFactory<>("time"));
        col_studentname.setCellValueFactory(new PropertyValueFactory<>("studentName"));

        tableCourse.setItems(null);
        tableCourse.setItems(data);

    }

    @FXML
    private void arrangeButtonAction(MouseEvent event) {
        String id = reg_txt_id.getText();
        String tutorName = add_tutorname.getText();
        String fatherName = add_fathername.getText();
        String time = add_time.getText();
        String studentName = add_studentName.getText();

        if (tutorName.equals("")
                || fatherName.equals("")
                || time.equals("")
                || studentName.equals("")) {
            JOptionPane.showMessageDialog(null, "Some Fields are missing!");
        } else {
            String update = "UPDATE parent_student set id = ?, fatherName = ?,studentName = ?, tutorName = ?, time = ? where id = '" + id + "' ";
            connection = DBHandler.connectDB();
            try {
                pst = connection.prepareStatement(update);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                pst.setString(1, reg_txt_id.getText());
                pst.setString(2, add_fathername.getText());
                pst.setString(3, add_studentName.getText());
                pst.setString(4, add_tutorname.getText());
                pst.setString(5, add_time.getText());
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Meeting has been Arranged");
                autoRefresh();
            } catch (SQLException ex) {
                Logger.getLogger(ViewPayment_Controller.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    @FXML
    private void displaySelectedAction(MouseEvent event) {
        ScheduleManage course = tableCourse.getSelectionModel().getSelectedItem();
        if (course == null) {
            JOptionPane.showMessageDialog(null, "Nothing Selected!");
        } else {
            String id = course.getId();
            String tutorName = course.gettutorName();
            String time = course.gettime();
            String fatherName = course.getfatherName();
            String studentName = course.getstudentName();

            reg_txt_id.setText(id);
            add_fathername.setText(fatherName);
            add_time.setText(time);
            add_tutorname.setText(tutorName);
            add_studentName.setText(studentName);

        }
    }

    @FXML
    private void back_btn_clicked(MouseEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();

        Stage stu_Menu = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Admin/Admin_Menu.fxml"));
        Scene scene = new Scene(root);
        stu_Menu.setScene(scene);
        stu_Menu.initStyle(StageStyle.TRANSPARENT);
        stu_Menu.show();
        stu_Menu.setResizable(false);

    }
}
