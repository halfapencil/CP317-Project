import java.util.Arrays;

// Object used to store the course info
public class CourseInfo {

    // Variables
    String studentId;
    String courseId;
    double marks[] = new double[3];

    // Constructor method
    public CourseInfo(String studentId, String courseId, double[] marks) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.marks = marks;
    }

    // toString
    @Override
    public String toString() {
        return "CourseInfo [studentId=" + studentId + ", courseId=" + courseId + ", marks=" + Arrays.toString(marks)
                + "]";
    }

    // Getters and setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public double[] getMarks() {
        return marks;
    }

    public void setMarks(double[] marks) {
        this.marks = marks;
    }

}
