package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author
 */
public class ScheduleManage {

    // model class holding getters, setters and properties
    private StringProperty id;
    private StringProperty tutorName;
    private StringProperty studentName;
    private StringProperty fatherName;
    private StringProperty time;

    public ScheduleManage(String id, String fatherName, String studentName, String tutorName, String time) {
        this.id = new SimpleStringProperty(id);
        this.fatherName = new SimpleStringProperty(fatherName);
        this.studentName = new SimpleStringProperty(studentName);
        this.tutorName = new SimpleStringProperty(tutorName);
        this.time = new SimpleStringProperty(time);
    }

    // Getters
    public String getId() {
        return id.get();
    }

    public String getfatherName() {
        return fatherName.get();
    }

    public String getstudentName() {
        return studentName.get();
    }

    public String gettutorName() {
        return tutorName.get();
    }

    public String gettime() {
        return time.get();
    }

    // Setters
    public void setId(String value) {
        id.set(value);
    }

    public void getfatherName(String value) {
        fatherName.set(value);
    }

    public void setstudentName(String value) {
        studentName.set(value);
    }

    public void settutorName(String value) {
        tutorName.set(value);
    }

    public void settime(String value) {
        time.set(value);
    }

    // Propert values
    public StringProperty idProperty() {
        return id;
    }

    public StringProperty tutorNameProperty() {
        return tutorName;
    }

    public StringProperty timeProperty() {
        return time;
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

    public StringProperty fatherNameProperty() {
        return fatherName;
    }

}
