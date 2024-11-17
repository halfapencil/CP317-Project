import java.io.File;

public class Main {

    public static void main(String[] args) {
        File cf = new File("CourseFile.txt");
        File nf = new File("NameFile.txt");
        new Processing(cf, nf);
    }

}
