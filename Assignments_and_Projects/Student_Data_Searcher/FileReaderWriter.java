import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Brad Kivell
 */
public class FileReaderWriter {

    public ArrayList<Student> studentList;
    public BinaryTree<Student> studentTree;
    public String key;

    public FileReaderWriter() {
        studentList = new ArrayList<>();
        studentTree = new BinaryTree<>();
        key = "Name";
    }

    // Reads file & inputs data to tree
    public void readFile(String fileName) {
        File f = new File(fileName);
        try {
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNextLine()) { // While not at end of file
                String line = fileScanner.nextLine(); // Read next line of file
                String[] splitString = line.split(", ", 2); // Splits into name and mark
                System.out.println("" + splitString[0] + "  :  " + Integer.parseInt(splitString[1])); // Displays student data to console
                Student student = new Student(splitString[0], Integer.parseInt(splitString[1])); // Generates student from line data
                student.setKey(student.name); // Default key to sort
                studentTree.add(student); // Adds student to tree
                studentList.add(student); // Adds student to list
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Failed to read file:  " + e.getMessage());
        }
    }

    // Writes text to file at filepath
    public void writeFile(String filePath, String text) {
        File file = new File(filePath); // Sets file
        try {
            file.createNewFile(); // Makes new file
            FileWriter fileWriter = new FileWriter(file); // Creates writer at file to write text
            fileWriter.write(text); // writes input string to file
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to write file: " + e.getMessage());
        }
    }

    // Sets new key for student and generates a new tree in order of new key comparator
    public void setKey(String key) {
        this.key = key; // Sets key to key
        System.out.println(key); // Prints new key to console
        studentTree = new BinaryTree(); // Creates tree
        if (key.equals("Name")) {
            for (int i = 0; i < studentList.size(); i++) { // Iterates through tree & reorders based on key
                studentList.get(i).setKey(studentList.get(i).name); 
                studentTree.add(studentList.get(i));
            }
        } else if (key.equals("Mark")) {
            for (int i = 0; i < studentList.size(); i++) {
                studentList.get(i).setKey(studentList.get(i).mark);
                studentTree.add(studentList.get(i));
            }
        }
    }

    // Returns a student object if found
    public Student findStudent(String searchString) {
        Student studentToFind; // Student to search for in tree
        try {
            if (key.equals("Name")) { // Checks for search key
                studentToFind = new Student(searchString, 0);
                studentToFind.setKey(searchString);
            } else {
                studentToFind = new Student(searchString, 0);
                studentToFind.setKey(Integer.parseInt(searchString)); // Passes mark as integer
            }
            studentToFind = studentTree.findNode(studentToFind); // Sets student to find to internal tree find method
        } catch (Exception e) { // Catches any input errors, returns null
            studentToFind = null; 
        }

        return studentToFind;
    }

}
