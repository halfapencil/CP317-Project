import java.io.File;

// Main method to run the program
public class Main {

    public static void main(String[] args) {

        // Uncomment to use the program without the GUI and replace file paths to
        // desired destinations
        /*
         * File cf = new File("CourseFile.txt");
         * File nf = new File("NameFile.txt");
         * File out = new File("out.txt");
         * new Processing(cf, nf, out);
         */

        // Open the GUI

        new UserInterface();
    }

}
