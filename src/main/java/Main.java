import java.sql.Date;

/**
 * Main class
 *
 * @author Pathum Danthanarayana, 101181411
 * @date March 10, 2024
 */
public class Main {

    /**
     * Main method
     */
    public static void main(String[] args) {
        // Create PostgresConnector
        StudentDBConnector studentDB = new StudentDBConnector("postgres", "postgres", 5433);

        // Get all students
        studentDB.getAllStudents();

        // Insert new student
        studentDB.addStudent("Pathum", "Danthanarayana", "pathumd@email.com", new Date(1710093600011L));
        studentDB.getAllStudents();

        // Update email of student
        studentDB.updateStudentEmail(4, "pathumd@example.com");
        studentDB.getAllStudents();
    }
}
