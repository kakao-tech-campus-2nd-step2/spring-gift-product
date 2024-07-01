package gift.DB;

import gift.model.ProductModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    // 데이터베이스 연결
    public Connection getConnection() throws SQLException {
        // schema.sql과 data.sql을 실행
        String url = "jdbc:h2:mem:test;INIT=runscript from 'classpath:schema.sql'\\;runscript from 'classpath:data.sql'";
        String username = "sa";
        String password = "";

        // 연결하기 위한 메서드
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) throws SQLException {
        DatabaseConnector connector = new DatabaseConnector();

        // 데이터베이스 연결
        Connection connection = connector.getConnection();
        System.out.println("Connected!");

        ProductModel productModel = new ProductModel(14545, "clothes", "nike", 106800);
        System.out.println("product: " + productModel);

        // 연결 종료
        connection.close();
        System.out.println("closed.");
    }
}