package Controllers.Parent;

//import Controllers.Student.*;
import Controllers.Admin.*;
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
public class Parent_MenuController implements Initializable {
    
    double xoffset, yoffset;

    @FXML
    private Button viewprogress;
    @FXML
    private Button communicate;
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
    private void viewprogressAction(MouseEvent event) throws IOException {
        viewprogress.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Parent/View_Progress.fxml"));
        Scene scene = new Scene(root);        
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void communicateAction(MouseEvent event) throws IOException {
        communicate.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Parent/Communication.fxml"));
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
