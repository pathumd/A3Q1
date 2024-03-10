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
    }
}
