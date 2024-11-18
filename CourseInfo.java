import java.util.Arrays;

// Object used to store the course info
public class CourseInfo {

    // Variables
    String studentId;
    String courseId;
    double grade[] = new double[3];

    // Constructor method
    public CourseInfo(String studentId, String courseId, double[] grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    // toString
    @Override
    public String toString() {
        return "CourseInfo [studentId=" + studentId + ", courseId=" + courseId + ", grade=" + Arrays.toString(grade)
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

    public double[] getGrade() {
        return grade;
    }

    public void setGrade(double[] grade) {
        this.grade = grade;
    }

}
