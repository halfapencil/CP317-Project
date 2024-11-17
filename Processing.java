import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Processing {

    ArrayList<CourseInfo> courseList = new ArrayList<CourseInfo>();
    ArrayList<NameInfo> nameList = new ArrayList<NameInfo>();
    ArrayList<Student> studentList = new ArrayList<Student>();

    Scanner courseScanner;
    Scanner nameScanner;

    public Processing(File course, File name) {
        try {
            courseScanner = new Scanner(course);
            nameScanner = new Scanner(name);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        fileInput(courseScanner, nameScanner);
        calculateGrades();
        sortStudentList();
        fileOutput();
    }

    private void fileInput(Scanner course, Scanner name) {
        while (course.hasNextLine()) {
            String[] l = course.nextLine().split(",");
            double grades[] = new double[4];
            for (int i = 2; i < 6; i++) {
                grades[i - 2] = Double.parseDouble(l[i]);
            }
            courseList.add(new CourseInfo(l[0], l[1], grades));
        }

        while (name.hasNextLine()) {
            String[] l = name.nextLine().split(",");
            nameList.add(new NameInfo(l[0], l[1]));
        }
    }

    private void calculateGrades() {
        for (CourseInfo c : courseList) {
            studentList.add(new Student(c.getStudentId(), c.getCourseId(), c.getMarks()));
        }

        for (NameInfo n : nameList) {
            for (Student s : studentList) {
                if (n.getStudentId().equals(s.getStudentId())) {
                    s.setName(n.getName());
                }
            }
        }
    }

    private void sortStudentList() {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < studentList.size() - 1; i++) {
                if (Integer.parseInt(studentList.get(i).getStudentId()) > Integer
                        .parseInt(studentList.get(i + 1).getStudentId())) {
                    swap(i);
                    swapped = true;
                }
            }
        } while (swapped);

        for (Student s : studentList) {
            System.out.println(s.toString());
        }
    }

    private void swap(int i) {
        Student s1 = studentList.get(i);
        Student s2 = studentList.get(i + 1);

        // Swap the students in the ArrayList
        studentList.set(i, s2);
        studentList.set(i + 1, s1);
    }
}
