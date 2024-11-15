public class Student {

    private String name;
    private float grade;
    private String sId;
    private String courseId;

    public Student(String name, String sId, String courseId, float[] grades) {
        this.name = name;
        this.sId = sId;
        this.courseId = courseId;
        this.grade = calculate(grades);
    }

    private double calculate(double[] grades) {

        return (grades[0] * 0.2) + (grades[1] * 0.2) + (grades[2] * 0.2) + (grades[3] * 0.4);
    }

}
