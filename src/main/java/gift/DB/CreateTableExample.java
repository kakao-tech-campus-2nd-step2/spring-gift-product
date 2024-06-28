package gift.DB;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class CreateTableExample {

    public static void main(String[] args) throws SQLException {
        // H2 URL
        String url = "jdbc:h2:mem:test;INIT=runscript from 'classpath:schema.sql';runscript from 'classpath:data.sql'";
        String username = "sa";  // 사용자 이름
        String password = "";    // 비밀번호 (기본은 빈 문자열)

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database initialized successfully.");

        // 데이터베이스 잘 작동되는지 검증하기
        String query = "SELECT * FROM product";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getLong("id") +
                    ", Category: " + resultSet.getString("category") +
                    ", Name: " + resultSet.getString("name") +
                    ", Price: " + resultSet.getLong("price"));
        }

        // 연결 종료
        connection.close();
    }
}