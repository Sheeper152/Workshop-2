package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

import java.sql.*;

public class UserDao {

    public static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Workshop_2?useSSL=false&characterEncoding=utf8&serverTimezone=UTC",
                    "root", "coderslab");
        } catch (SQLException e) { e.printStackTrace(); }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { e.printStackTrace(); }
            }
        }


        PreparedStatement preStmt = conn.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        ResultSet rs = preStmt.getGeneratedKeys();
        if (rs.next()) {
            long id = rs.getLong(1);
            System.out.println("Inserted ID:" + id);
        }
    }
    public String hashPassword (String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

}
