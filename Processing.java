import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Processing {
    ArrayList<Student> studentList = new ArrayList<Student>();

    public Processing(File course, File name) {
        try {
            Scanner courseScanner = new Scanner(course);
            Scanner nameScanner = new Scanner(name);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        fileInput(course, name);
    }

    private void fileInput(File course, File name) {

    }

}
