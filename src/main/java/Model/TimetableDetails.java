package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author
 */
public class TimetableDetails {

    // model class holding getters, setters and properties
    private StringProperty id;
    private StringProperty courseName;
    private StringProperty courseID;
    private StringProperty tutorName;
    private StringProperty courseDuration;
    private StringProperty assignmentDuration;

    public TimetableDetails(String id, String courseName, String courseID, String tutorName, String courseDuration, String assignmentDuration) {
        this.id = new SimpleStringProperty(id);
        this.courseName = new SimpleStringProperty(courseName);
        this.courseID = new SimpleStringProperty(courseID);
        this.tutorName = new SimpleStringProperty(tutorName);
        this.courseDuration = new SimpleStringProperty(courseDuration);
        this.assignmentDuration = new SimpleStringProperty(assignmentDuration);
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

    public String getcourseDuration() {
        return courseDuration.get();
    }

    public String getassignmentDuration() {
        return assignmentDuration.get();
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

    public void setcourseDuration(String value) {
        courseDuration.set(value);
    }

    public void setassignmentDuration(String value) {
        assignmentDuration.set(value);
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

    public StringProperty courseDurationProperty() {
        return courseDuration;
    }

    public StringProperty assignmentDurationProperty() {
        return assignmentDuration;
    }

}
