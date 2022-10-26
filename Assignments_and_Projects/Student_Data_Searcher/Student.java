/**
 *
 * @author Brad Kivell (20115449)
 * @param <E>
 */
public class Student<E extends Comparable> implements Comparable<Student> {

    public int mark;
    public String name;
    public E key; // Stores key to be sorted by

    public Student(String newName, int newMark) {
        this.mark = newMark;
        this.name = newName;
    }

    @Override
    public int compareTo(Student student) {
        return this.key.compareTo(student.key); // Returns comparison of student key objects
    }

    // Sets key to input key
    public void setKey(E newKey) {
        this.key = newKey;
    }

    // Returns string in form of “Name: studentName Mark: student mark”. 
    @Override
    public String toString() {
        return ("Name: " + this.name + " Mark: " + this.mark);
    }

}
