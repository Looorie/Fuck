
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper {
    //java4interface	创建表的名字

    private static final String url = "jdbc:mysql://localhost:3306/"+
            DatabaseConstant.DB_NAME+"?useUnicode=true&characterEncoding=UTF-8";
    private static final String name = "com.mysql.jdbc.Driver";
    private Connection connection = null;
    public PreparedStatement preparedStatement = null;

    public DBHelper(String sql) {
        try {
            Class.forName(name).newInstance();
            connection = DriverManager.getConnection(url, DatabaseConstant.USER, DatabaseConstant.PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.connection.close();
            this.preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}