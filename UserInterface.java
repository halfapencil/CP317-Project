import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

// GUI class
public class UserInterface extends JFrame {

    // Variables
    JLabel courseFileDir = new JLabel("None");
    JLabel nameFileDir = new JLabel("None");
    JLabel outputFileDir = new JLabel("None");

    JLabel courseErrorLabel = new JLabel();
    JLabel nameErrorLabel = new JLabel();

    static JLabel convertConfirmation = new JLabel();

    JButton courseFileButton;
    JButton nameFileButton;
    JButton outputButton;
    JButton convertButton;

    Processing process;
    File course;
    File name;
    File out;

    // Constructor method
    public UserInterface() {
        setupWindow();
        setupFileChoosers();
    }

    // Sets up the details for the GUI
    private void setupWindow() {
        setTitle("Grade Calculator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Using absolute layout for simplicity
        setVisible(true);
    }

    // Sets up the file Chooser buttons and their labels
    private void setupFileChoosers() {

        // Create buttons
        courseFileButton = new JButton("Course File");
        nameFileButton = new JButton("Name File");
        outputButton = new JButton("Output Destination");
        convertButton = new JButton("Convert");

        // Set the bounds
        courseFileButton.setBounds(10, 50, 200, 30);
        courseFileDir.setBounds(10, 100, 400, 30);
        nameFileButton.setBounds(10, 150, 200, 30);
        nameFileDir.setBounds(10, 200, 400, 30);
        outputButton.setBounds(10, 250, 200, 30);
        outputFileDir.setBounds(10, 300, 400, 30);
        convertButton.setBounds(10, 350, 200, 30);
        convertConfirmation.setBounds(10, 400, 400, 30);
        courseErrorLabel.setBounds(220, 20, 200, 90);
        nameErrorLabel.setBounds(220, 120, 200, 90);

        // Add to the window
        add(courseFileButton);
        add(nameFileButton);
        add(outputButton);
        add(convertButton);

        add(courseFileDir);
        add(nameFileDir);
        add(outputFileDir);
        add(convertConfirmation);

        add(courseErrorLabel);
        add(nameErrorLabel);
        // Add action listeners to allow buttons to function
        courseFileButton.addActionListener(e -> course = openFileChooser("course"));
        nameFileButton.addActionListener(e -> name = openFileChooser("name"));
        outputButton.addActionListener(e -> out = outputFileCreator());
        convertButton.addActionListener(e -> convert());
    }

    // Action for input file chooser
    private File openFileChooser(String type) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {

            // Add text to show the destination if a course file is chosen
            if (type.equals("course")) {
                courseFileDir.setText("Destination:" + fileChooser.getSelectedFile().getAbsolutePath());
            }
            // Add text to show the destination if a name file is chosen
            else if (type.equals("name")) {
                nameFileDir.setText("Destination" + fileChooser.getSelectedFile().getAbsolutePath());
            }

            // Return the file selected
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }

    }

    // Action for choosing output file destination
    private File outputFileCreator() {
        JFileChooser fileChooser = new JFileChooser();

        // Sets the starting directory to the current file path
        fileChooser.setCurrentDirectory(new File("."));
        int result = fileChooser.showOpenDialog(this);

        // If a file is chosen
        if (result == JFileChooser.APPROVE_OPTION) {
            File selected = fileChooser.getSelectedFile();

            // If a new file is created, force a .txt suffix
            if (!selected.getName().toLowerCase().endsWith(".txt")) {
                selected = new File(selected.getAbsolutePath() + ".txt");
            }

            try {

                // If an existing file is chosen, return the file
                if (!selected.exists()) {

                    // If the chosen file does not exist yet, create a new text file and return the
                    // file
                    if (selected.createNewFile()) {

                        outputFileDir.setText("Destination:" + selected.getAbsolutePath());
                        return selected;
                    }
                }

                // Change the label to show the destination
                outputFileDir.setText("Destination:" + selected.getAbsolutePath());
                return selected;
            } catch (IOException er) {

            }
        }
        return null;
    }

    // Action for the convert button
    private void convert() {

        // If the three files exists, process the inputs and output to the output file
        if (course != null && name != null && out != null) {
            process = new Processing(course, name, out);
            courseFileButton.setForeground(Color.BLACK);
            nameFileButton.setForeground(Color.BLACK);
            outputButton.setForeground(Color.BLACK);
            convertConfirmation.setText("Successfully converted");
        } else {
            if (course == null) {
                courseFileButton.setForeground(Color.RED);
            }

            if (name == null) {
                nameFileButton.setForeground(Color.RED);
            }
            if (out == null) {
                outputButton.setForeground(Color.RED);
            }
            errorMessage(convertConfirmation, "Please add missing files");
        }
        if (process.getCourseError()) {
            errorMessage(courseErrorLabel, process.getCourseErrMessage());
        }
        if (process.getNameError()) {
            errorMessage(nameErrorLabel, process.getNameErrMessage());
        }

    }

    public static void errorMessage(JLabel label, String str) {
        label.setText(str);

    }
}