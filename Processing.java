import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Processing {
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
    }

    private void fileInput(Scanner course, Scanner name) {
        while (course.hasNextLine()) {
            String[] courseInfo = course.nextLine().split(",");
            if (name.hasNextLine()) {
                String[] nameInfo = name.nextLine().split(",");
            }
            studentList.add(new Student(null, null, null, null));
        }
    }

}
