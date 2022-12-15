package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author
 */
public class ProgressDetails {

    // model class holding getters, setters and properties
    private StringProperty id;
    private StringProperty courseName;
    private StringProperty courseID;
    private StringProperty tutorName;
    private StringProperty givenMarks;
    private StringProperty question;
    private StringProperty answer;
    private StringProperty studentName;

    public ProgressDetails(String id, String courseName, String courseID, String tutorName, String givenMarks, String question, String answer, String studentName) {
        this.id = new SimpleStringProperty(id);
        this.courseName = new SimpleStringProperty(courseName);
        this.courseID = new SimpleStringProperty(courseID);
        this.tutorName = new SimpleStringProperty(tutorName);
        this.givenMarks = new SimpleStringProperty(givenMarks);
        this.question = new SimpleStringProperty(question);

        this.answer = new SimpleStringProperty(answer);
        this.studentName = new SimpleStringProperty(studentName);
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

    public String getgivenMarks() {
        return givenMarks.get();
    }

    public String getquestion() {
        return question.get();
    }

    public String getanswer() {
        return answer.get();
    }

    public String getstudentName() {
        return studentName.get();
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

    public void setgivenMarks(String value) {
        givenMarks.set(value);
    }

    public void setquestion(String value) {
        question.set(value);
    }

    public void setanswer(String value) {
        answer.set(value);
    }

    public void setstudentName(String value) {
        studentName.set(value);
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

    public StringProperty givenMarksProperty() {
        return givenMarks;
    }

    public StringProperty questionProperty() {
        return question;
    }

    public StringProperty answerProperty() {
        return answer;
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

}
