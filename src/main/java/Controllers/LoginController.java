package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author 
 */
public class LoginController implements Initializable {
    double xoffset, yoffset;

    @FXML
    private Button btn_signIn;
    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private CheckBox pass_toggle;
    @FXML
    private TextField txt_pword;
    @FXML
    private Button btnClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.togglevisiblePassword(null);
    }

    @FXML
    private void signInButtonAction(MouseEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();
        if (password.equals("") || username.equals("")) {
            JOptionPane.showMessageDialog(null, "Fields Required");
        } //username=admin & password=sec
        else if (username.equals("durham") && password.equals("tutor")) {
            btn_signIn.getScene().getWindow().hide();

            Stage MenuCompo = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Tutor/Tutor_Menu.fxml"));
            Scene scene = new Scene(root);
            MenuCompo.initStyle(StageStyle.TRANSPARENT);
            //scene.setFill(Color.TRANSPARENT);
            MenuCompo.setScene(scene);
            MenuCompo.show();
            MenuCompo.setResizable(false);
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
                    MenuCompo.setX(event.getScreenX() - xoffset);
                    MenuCompo.setY(event.getScreenY() - yoffset);
                }
            });
        }
        else if (username.equals("durham") && password.equals("student")) {
            btn_signIn.getScene().getWindow().hide();

            Stage MenuCompo = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/Student_Menu.fxml"));
            Scene scene = new Scene(root);
            MenuCompo.initStyle(StageStyle.TRANSPARENT);
            //scene.setFill(Color.TRANSPARENT);
            MenuCompo.setScene(scene);
            MenuCompo.show();
            MenuCompo.setResizable(false);
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
                    MenuCompo.setX(event.getScreenX() - xoffset);
                    MenuCompo.setY(event.getScreenY() - yoffset);
                }
            });
        }
        else if (username.equals("durham") && password.equals("admin")) {
            btn_signIn.getScene().getWindow().hide();

            Stage MenuCompo = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Admin/Admin_Menu.fxml"));
            Scene scene = new Scene(root);
            MenuCompo.initStyle(StageStyle.TRANSPARENT);
            //scene.setFill(Color.TRANSPARENT);
            MenuCompo.setScene(scene);
            MenuCompo.show();
            MenuCompo.setResizable(false);
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
                    MenuCompo.setX(event.getScreenX() - xoffset);
                    MenuCompo.setY(event.getScreenY() - yoffset);
                }
            });
        }
        else if (username.equals("durham") && password.equals("parent")) {
            btn_signIn.getScene().getWindow().hide();

            Stage MenuCompo = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Parent/Parent_Menu.fxml"));
            Scene scene = new Scene(root);
            MenuCompo.initStyle(StageStyle.TRANSPARENT);
            //scene.setFill(Color.TRANSPARENT);
            MenuCompo.setScene(scene);
            MenuCompo.show();
            MenuCompo.setResizable(false);
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
                    MenuCompo.setX(event.getScreenX() - xoffset);
                    MenuCompo.setY(event.getScreenY() - yoffset);
                }
            });
        }
        else {
            JOptionPane.showMessageDialog(null, "Incorrect username or password");
        }
    }

    @FXML
    private void togglevisiblePassword(ActionEvent event) {
        if (pass_toggle.isSelected()) {
            txt_pword.setText(txt_password.getText());
            txt_password.setVisible(false);
            txt_pword.setVisible(true);
        }
        else{
            txt_password.setText(txt_pword.getText());
            txt_password.setVisible(true);
            txt_pword.setVisible(false);
        }
    }
    
    @FXML
    public void closeLogin(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
