import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
