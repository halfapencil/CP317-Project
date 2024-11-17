public class Student {

    private String studentId;
    private String name;
    private String courseId;
    private String grade;

    public Student(String studentId, String courseId, double[] grades) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = calculate(grades);
    }

    private String calculate(double[] grades) {
        double value = (grades[0] * 0.2) + (grades[1] * 0.2) + (grades[2] * 0.2) + (grades[3] * 0.4);
        return String.format("%.1f", value);
    }

    @Override
    public String toString() {
        return studentId + "," + name + "," + courseId + "," + grade;
    }

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
