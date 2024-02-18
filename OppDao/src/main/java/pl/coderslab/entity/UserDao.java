package pl.coderslab.entity;

import com.mysql.cj.callback.UsernameCallback;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

import java.sql.*;

public class UserDao {

    public static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    private static final String READ_USER_QUERY = "SELECT * FROM users where id = ?";

    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? where id = ?";

    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";

    private  static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";

    public static void main(String[] args) throws SQLException {

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Workshop_2?useSSL=false&characterEncoding=utf8&serverTimezone=UTC",
                    "root", "coderslab");

        String sql = "SELECT * FROM users WHERE user_name = ? ;";

        PreparedStatement preStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        preStmt.executeQuery();

    ResultSet rs = preStmt.getGeneratedKeys();
        if (rs.next()) {
            long id = rs.getLong(1);
            System.out.println("Inserted ID:" + id);
        }
    }
}

