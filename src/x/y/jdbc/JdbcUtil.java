package x.y.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

    private static String driverClass = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        Properties p = new Properties();
        InputStream is = null;
        try {
            is = JdbcUtil.class.getResourceAsStream("jdbc.properties");
            p.load(is);
            driverClass = p.getProperty("driverClass");
            url = p.getProperty("url");
            username = p.getProperty("username");
            password = p.getProperty("username");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        Connection conn = tl.get();
        if (conn == null) {
            try {
                Class.forName(driverClass);
                conn= DriverManager.getConnection(url,username,password);
                tl.set(conn);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeConnection(){
        Connection connection=tl.get();
        try {
            if (connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
