import java.sql.*;

/**
 * PostgresConnector class
 *
 * @author Pathum Danthanarayana, 101181411
 * @date March 10, 2024
 */
public class StudentDBConnector {
    // Fields
    private String username;
    private String password;
    private int portNumber;
    private Connection connection;

    /**
     * Constructor
     */
    public StudentDBConnector(String username, String password, int portNumber) {
        // Initialize fields
        this.username = username;
        this.password = password;
        this.portNumber = portNumber;
        // Connect to DB
        connectToDB();
    }

    private void connectToDB() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(String.format("jdbc:postgresql://localhost:%d/StudentManagementSystem", portNumber), username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        // Check connection
        if (connection != null) {
            System.out.println("Successfully connected to StudentManagementSystem");
        }
        else {
            System.out.println("Connection to StudentManagementSystem unsuccessful");
        }
    }

    /** Operation methods **/

    /**
     * Retrieves information of all students in the students table.
     */
    public void getAllStudents() {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");

            // Get result of query
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()) {
                int student_id = resultSet.getInt(1);
                String first_name = resultSet.getString(2);
                String last_name = resultSet.getString(3);
                String email = resultSet.getString(4);
                Date enrollment_date = resultSet.getDate(5);
                System.out.println(String.format("%d: %s, %s, %s, %s", student_id, first_name, last_name, email, enrollment_date));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a new student to the students table.
     * @param firstName - the first name of the student
     * @param lastName - the last name of the student
     * @param email - the email of the student
     * @param enrollmentDate - the enrollment date of the student
     */
    public void addStudent(String firstName, String lastName, String email, Date enrollmentDate) {
        try {
            // Insert entry into table using prepared statement
            PreparedStatement statement = connection.prepareStatement("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setDate(4, enrollmentDate);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the email of the specified student.
     * @param studentId - the student ID of the student
     * @param newEmail - the new email of the student
     */
    public void updateStudentEmail(int studentId, String newEmail) {
        try {
            // Update entry in table using prepared statement
            PreparedStatement statement = connection.prepareStatement("UPDATE students SET email=? WHERE student_id=?");
            statement.setString(1, newEmail);
            statement.setInt(2, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
