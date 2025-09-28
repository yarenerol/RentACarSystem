package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private DBUtil() {}

    private static String URL = "jdbc:postgresql://localhost:5432/rent-a-car";
    private static String username = "postgres";
    private static String pswrd = "postgres";


    public static Connection getConnection () throws SQLException {


        return DriverManager.getConnection(URL, username, pswrd);
    }
}
