import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Class for input, processing input, sorting, and output
public class Processing {

    // Variables
    ArrayList<CourseInfo> courseList = new ArrayList<CourseInfo>();
    ArrayList<NameInfo> nameList = new ArrayList<NameInfo>();
    ArrayList<Student> studentList = new ArrayList<Student>();

    Scanner courseScanner;
    Scanner nameScanner;

    // Constructor method that takes course and name as inputs and out as an output
    // destination
    public Processing(File course, File name, File out) {
        try {

            // Scanners for file reading
            courseScanner = new Scanner(course);
            nameScanner = new Scanner(name);

            fileInput(courseScanner, nameScanner);
            SetupStudent();
            sortStudentList();
            fileOutput(out);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // Processing file input
    private void fileInput(Scanner course, Scanner name) {

        // Loops through entire file
        while (course.hasNextLine()) {

            // CSV structured like StudentID, Course, Test 1, Test 2, Test 3, Final Exam
            String[] l = course.nextLine().split(",");
            double grades[] = new double[4];

            // Getting all the grades
            for (int i = 2; i < 6; i++) {
                grades[i - 2] = Double.parseDouble(l[i]);
            }
            courseList.add(new CourseInfo(l[0], l[1], grades));
        }

        // Loops through name files
        while (name.hasNextLine()) {

            // CSV structured like studentID,Name
            String[] l = name.nextLine().split(",");
            nameList.add(new NameInfo(l[0], l[1]));
        }
    }

    // setup the student list to prepare for output
    private void SetupStudent() {

        // Using the courses to collect Student ID, Course ID, and Marks
        for (CourseInfo c : courseList) {
            studentList.add(new Student(c.getStudentId(), c.getCourseId(), c.getMarks()));
        }

        // uses the names list to match student IDs and names
        for (NameInfo n : nameList) {
            for (Student s : studentList) {
                if (n.getStudentId().equals(s.getStudentId())) {
                    s.setName(n.getName());
                }
            }
        }
    }

    // Sorts the student list by student number
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
    }

    // Helper method for sorting
    private void swap(int i) {
        Student s1 = studentList.get(i);
        Student s2 = studentList.get(i + 1);

        // Swap the students in the ArrayList
        studentList.set(i, s2);
        studentList.set(i + 1, s1);
    }

    private void fileOutput(File outputFile) {
        try {
            FileWriter out = new FileWriter(outputFile);
            for (Student s : studentList) {
                out.write(s.toString() + "\n");
            }
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
