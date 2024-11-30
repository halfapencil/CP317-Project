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

    static String errMessage = "";
    
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
            int count = 1;
    
    
    
            // Loops through entire file
            while (course.hasNextLine()) {
    
                // CSV structured like StudentID, Course, Test 1, Test 2, Test 3, Final Exam
                String[] l = course.nextLine().split(",");
                double grades[] = new double[4];
                boolean error = false;
    
                // Getting all the grades
                for (int i = 2; i < 6; i++) {
                    try {
                        if (Double.parseDouble(l[i]) < 0){
                            error = true;
                        } else 
                            grades[i-2] = Double.parseDouble(l[i]);
                        
                    } catch (Exception e) {
                        error = true;
                    }
                }
    
                count++;
    
                if (error){
                    errMessage = errMessage + count + ",";
                }
                else 
                    courseList.add(new CourseInfo(l[0], l[1], grades));
    
            }
            if (errMessage.length() != 0){
                errMessage = errMessage + " These lines contain invalid grades.";
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
    
        public static String getErrMessage() {
            return errMessage;
    }
}
