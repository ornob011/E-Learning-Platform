package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author
 */
public class CommunicationDetails {

    // model class holding getters, setters and properties
    private StringProperty id;
    private StringProperty courseName;
    private StringProperty fatherName;
    private StringProperty tutorName;
    private StringProperty studentName;
    private StringProperty parentMessage;

    public CommunicationDetails(String id, String courseName, String fatherName, String tutorName, String studentName, String parentMessage) {
        this.id = new SimpleStringProperty(id);
        this.courseName = new SimpleStringProperty(courseName);
        this.fatherName = new SimpleStringProperty(fatherName);
        this.tutorName = new SimpleStringProperty(tutorName);
        this.studentName = new SimpleStringProperty(studentName);
        this.parentMessage = new SimpleStringProperty(parentMessage);
    }

    // Getters
    public String getId() {
        return id.get();
    }

    public String getcourseName() {
        return courseName.get();
    }

    public String getfatherName() {
        return fatherName.get();
    }

    public String getstudentName() {
        return studentName.get();
    }

    public String getparentMessage() {
        return parentMessage.get();
    }

    public String gettutorName() {
        return tutorName.get();
    }

    // Setters
    public void setId(String value) {
        id.set(value);
    }

    public void setcourseName(String value) {
        courseName.set(value);
    }

    public void setfatherName(String value) {
        fatherName.set(value);
    }

    public void settutorName(String value) {
        tutorName.set(value);
    }

    public void setstudentName(String value) {
        studentName.set(value);
    }

    public void setparentMessage(String value) {
        parentMessage.set(value);
    }

    // Propert values
    public StringProperty idProperty() {
        return id;
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }

    public StringProperty fatherNameProperty() {
        return fatherName;
    }

    public StringProperty tutorNameProperty() {
        return tutorName;
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

    public StringProperty parentMessageProperty() {
        return parentMessage;
    }

}
