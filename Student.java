// Object that stores the student information for output
public class Student {

    // Variables
    private String studentId;
    private String name;
    private String courseId;
    private String grade;

    // Constructor
    public Student(String studentId, String courseId, double[] grades) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = calculate(grades);
    }

    // Calculate the grades using test 1,2,3 x 20%, and final worth 40%
    private String calculate(double[] grades) {
        double value = (grades[0] * 0.2) + (grades[1] * 0.2) + (grades[2] * 0.2) + (grades[3] * 0.4);
        return String.format("%.1f", value);
    }

    // toString method that returns a CSV of studentID,name,courseID,grade
    @Override
    public String toString() {
        return studentId + "," + name + "," + courseId + "," + grade;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

}
