module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires mysql.connector.java;
	requires java.sql;
	requires java.desktop;
    opens org.openjfx.lms to javafx.fxml;
    opens Controllers to javafx.fxml;
    opens Controllers.Admin to javafx.fxml;
    opens Controllers.Parent to javafx.fxml;
    opens Controllers.Student to javafx.fxml;
    opens Controllers.Tutor to javafx.fxml;
    opens DBConnection to javafx.fxml;
    opens Model to javafx.base;
    
    
    exports org.openjfx.lms;
}