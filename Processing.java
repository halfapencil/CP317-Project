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

    String courseErrMessage = "Line ";
    String nameErrMessage = "Line ";

    boolean courseError = false;
    boolean nameError = false;

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
        int count = 0;
        // Loops through entire file
        while (course.hasNextLine()) {
            count++;
            // CSV structured like StudentID, Course, Test 1, Test 2, Test 3, Final Exam
            String[] l = course.nextLine().split(",");
            double grades[] = new double[4];
            boolean error = false;

            // Error: Missing values
            if (l.length != 6) {
                courseErrMessage = courseErrMessage + count + ",";
                error = true;
            } else {
                for (String s : l) {
                    if (s.trim().isEmpty()) {
                        courseErrMessage = courseErrMessage + count + ",";
                        error = true;
                        break;
                    }
                }
            }

            if (error) {
                courseError = true;
                continue;
            }
            // Getting all the grades
            for (int i = 2; i < 6; i++) {
                try {
                    // Error: Negative grades
                    if (Double.parseDouble(l[i]) < 0) {
                        error = true;
                        break;
                    } else
                        grades[i - 2] = Double.parseDouble(l[i]);

                } catch (Exception e) {
                    error = true;
                }
            }

            // List the lines which contain illegal inputs
            if (error) {
                courseError = true;
                courseErrMessage = courseErrMessage + count + ",";
            } else
                courseList.add(new CourseInfo(l[0], l[1], grades));
        }

        // End the error message
        if (courseErrMessage.length() != 0) {
            courseErrMessage = courseErrMessage + " contain invalid inputs ";
        }

        // Loops through name files
        count = 0;
        while (name.hasNextLine()) {
            count++;
            boolean error = false;
            // CSV structured like studentID,Name
            String[] l = name.nextLine().split(",");

            if (l.length != 2 || l[0].trim().isEmpty() || l[1].trim().isEmpty()) {
                error = true;

            } else
                nameList.add(new NameInfo(l[0], l[1]));

            if (error) {
                nameError = true;
                nameErrMessage = nameErrMessage + count + ",";
            }
        }
        System.out.println(nameList);
        nameErrMessage = nameErrMessage + "contains invalid inputs ";
    }

    // setup the student list to prepare for output
    private void SetupStudent() {
        // Using the courses to collect Student ID, Course ID, and Marks
        for (CourseInfo c : courseList) {
            studentList.add(new Student(c.getStudentId(), c.getCourseId(), c.getGrade()));
        }

        // uses the names list to match student IDs and names
        for (NameInfo n : nameList) {
            for (Student s : studentList) {
                if (n.getStudentId().equals(s.getStudentId())) {
                    s.setName(n.getName());
                }
            }
        }

        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getName() == null || studentList.get(i).getClass() == null
                    || studentList.get(i).getStudentId() == null)
                studentList.remove(i);
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

    // Method writes to the output file
    private void fileOutput(File outputFile) {
        try {
            // Create a filewriter
            FileWriter out = new FileWriter(outputFile);
            for (Student s : studentList) {
                // For every student, write the students information to the file
                out.write(s.toString() + "\n");
            }

            // Close the filewriter
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getCourseErrMessage() {
        return "<html>" + courseErrMessage + "in Course File " + "</html>";
    }

    public String getNameErrMessage() {
        return "<html>" + nameErrMessage + "in Name File " + "</html>";
    }

    public boolean getNameError() {
        return nameError;
    }

    public boolean getCourseError() {
        return courseError;
    }
}
