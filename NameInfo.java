// Object used to store information about studentID and student name
public class NameInfo {

    // Variables
    String studentId;
    String name;

    // Constructor method
    public NameInfo(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    // toSstring method
    @Override
    public String toString() {
        return "NameInfo [studentId=" + studentId + ", name=" + name + "]";
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
