package Server.Database;

/**
 * MySQL values
 */
public interface DatabaseCreds {
    /**
     * @value driver for mySQL
     */
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * @value the URL of mySQL
     */
    static final String DB_URL = "jdbc:mysql://localhost/mydb";
    /**
     * @value username for mySQL
     */
    static final String USERNAME = "root";
    /**
     * @value password for mySQL
     */
    static final String PASSWORD = "whitened";
}
