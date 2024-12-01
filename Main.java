import java.io.File;

// Main method to run the program
public class Main {

    public static void main(String[] args) {

        // Testing the program using the console.
        // test();

        // Open the GUI
        new UserInterface();
    }

    private void test() {
        File cf = new File("CourseFile.txt");
        File nf = new File("NameFile.txt");
        File out = new File("out.txt");
        Processing p = new Processing(cf, nf, out);
        System.out.println("Done");
        System.out.println(p.getCourseErrMessage());
        System.out.println(p.getNameErrMessage());

    }
}
