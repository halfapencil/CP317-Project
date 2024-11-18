import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class UserInterface extends JFrame {

    JLabel courseFileDir = new JLabel();
    JLabel nameFileDir = new JLabel();
    JLabel outputFileDir = new JLabel();
    JLabel convertConfirmation = new JLabel();

    File course;
    File name;
    File out;

    public UserInterface() {
        setupWindow();
        setupFileChoosers();
    }

    private void setupWindow() {
        setTitle("Grade Calculator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Using absolute layout for simplicity
        setVisible(true);
    }

    private void setupFileChoosers() {

        JButton courseFileButton = new JButton("Course File");
        JButton nameFileButton = new JButton("NameButton");
        JButton outputButton = new JButton("Output Destination");
        JButton convertButton = new JButton("Convert");

        courseFileButton.setBounds(10, 50, 200, 30);
        courseFileDir.setBounds(10, 100, 400, 30);
        nameFileButton.setBounds(10, 150, 200, 30);
        nameFileDir.setBounds(10, 200, 400, 30);
        outputButton.setBounds(10, 250, 200, 30);
        outputFileDir.setBounds(10, 300, 400, 30);
        convertButton.setBounds(10, 350, 200, 30);
        convertConfirmation.setBounds(10, 400, 400, 30);

        add(courseFileButton);
        add(nameFileButton);
        add(outputButton);
        add(convertButton);

        add(courseFileDir);
        add(nameFileDir);
        add(outputFileDir);

        courseFileButton.addActionListener(e -> course = openFileChooser("course"));
        nameFileButton.addActionListener(e -> name = openFileChooser("name"));
        outputButton.addActionListener(e -> out = outputFileCreator());
        convertButton.addActionListener(e -> convert());
    }

    private File openFileChooser(String type) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {

            if (type.equals("course")) {
                courseFileDir.setText("Destination:" + fileChooser.getSelectedFile().getAbsolutePath());
            } else if (type.equals("name")) {
                nameFileDir.setText("Destination" + fileChooser.getSelectedFile().getAbsolutePath());
            }
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }

    }

    private File outputFileCreator() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selected = fileChooser.getSelectedFile();
            if (!selected.getName().toLowerCase().endsWith(".txt")) {
                selected = new File(selected.getAbsolutePath() + ".txt");
            }

            try {
                if (!selected.exists()) {
                    if (selected.createNewFile()) {

                        outputFileDir.setText("Destination:" + selected.getAbsolutePath());
                        return selected;
                    }
                }
                outputFileDir.setText("Destination:" + selected.getAbsolutePath());
                return selected;
            } catch (IOException er) {

            }
        }
        return null;
    }

    private void convert() {
        if (course.exists() && name.exists() && out.exists())
            new Processing(course, name, out);
        convertConfirmation.setText("Successfully converted");

    }
}