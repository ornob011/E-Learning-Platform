package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author
 */
public class EnrollmentDetails {

    // model class holding getters, setters and properties
    private StringProperty id;
    private StringProperty courseName;
    private StringProperty courseID;
    private StringProperty tutorName;
    private StringProperty studentName;
    private StringProperty regNo;

    public EnrollmentDetails(String id, String courseName, String courseID, String tutorName, String studentName, String regNo) {
        this.id = new SimpleStringProperty(id);
        this.courseName = new SimpleStringProperty(courseName);
        this.courseID = new SimpleStringProperty(courseID);
        this.tutorName = new SimpleStringProperty(tutorName);
        this.studentName = new SimpleStringProperty(studentName);
        this.regNo = new SimpleStringProperty(regNo);
    }

    // Getters
    public String getId() {
        return id.get();
    }

    public String getcourseName() {
        return courseName.get();
    }

    public String getcourseID() {
        return courseID.get();
    }

    public String gettutorName() {
        return tutorName.get();
    }

    public String getstudentName() {
        return studentName.get();
    }

    public String getregNo() {
        return regNo.get();
    }

    // Setters
    public void setId(String value) {
        id.set(value);
    }

    public void setcourseName(String value) {
        courseName.set(value);
    }

    public void setcourseID(String value) {
        courseID.set(value);
    }

    public void settutorName(String value) {
        tutorName.set(value);
    }

    public void setstudentName(String value) {
        studentName.set(value);
    }

    public void setregNo(String value) {
        regNo.set(value);
    }

    // Propert values
    public StringProperty idProperty() {
        return id;
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }

    public StringProperty courseIDProperty() {
        return courseID;
    }

    public StringProperty tutorNameProperty() {
        return tutorName;
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

    public StringProperty regNoProperty() {
        return regNo;
    }

}
