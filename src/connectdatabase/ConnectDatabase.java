package connectdatabase;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase implements Serializable {
    static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String user = "system";
    static final String password = "1";

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
