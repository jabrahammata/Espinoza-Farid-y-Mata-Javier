package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class h2hot {
    public static Connection getConnection() throws SQLException, ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./consultorio","sa","sa");
    }
}
