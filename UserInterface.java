import javax.swing.*;
import javax.swing.event.AncestorListener;

public class UserInterface extends JFrame implements AncestorListener {

    public UserInterface() {
        setupWindow();
        setupFileChoosers();
    }

    private void setupWindow() {
        JFrame frame = new JFrame("Grade Calculator");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void setupFileChoosers() {
        JFileChooser courseFile1 = new JFileChooser();
        JFileChooser nameFile2 = new JFileChooser();
        JFileChooser outputFile = new JFileChooser();
    }
}