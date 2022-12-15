package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author
 */
public class CourseDetails {

    // model class holding getters, setters and properties
    private StringProperty id;
    private StringProperty courseName;
    private StringProperty courseID;
    private StringProperty tutorName;
    private StringProperty assignedMarks;
    private StringProperty courseContent;

    public CourseDetails(String id, String courseName, String courseID, String tutorName, String assignedMarks, String courseContent) {
        this.id = new SimpleStringProperty(id);
        this.courseName = new SimpleStringProperty(courseName);
        this.courseID = new SimpleStringProperty(courseID);
        this.tutorName = new SimpleStringProperty(tutorName);
        this.assignedMarks = new SimpleStringProperty(assignedMarks);
        this.courseContent = new SimpleStringProperty(courseContent);
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

    public String getassignedMarks() {
        return assignedMarks.get();
    }

    public String getcourseContent() {
        return courseContent.get();
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

    public void setassignedMarks(String value) {
        assignedMarks.set(value);
    }

    public void setcourseContent(String value) {
        courseContent.set(value);
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

    public StringProperty assignedMarksProperty() {
        return assignedMarks;
    }

    public StringProperty courseContentProperty() {
        return courseContent;
    }

}
