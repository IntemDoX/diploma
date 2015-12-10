import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseTest {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://hostname:port/dbname", "username", "password");
            conn.close();
        }catch (Exception e){}


    }
}
