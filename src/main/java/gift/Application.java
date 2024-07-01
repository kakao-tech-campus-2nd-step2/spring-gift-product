package gift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static Connection getConnection() throws Exception {
        var url = "jdbc:h2:mem:test";
        var user = "";
        var password = "";
        return DriverManager.getConnection(url, user, password);
    }
}
