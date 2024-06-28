package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        jdbcTemplate.execute("DROP TABLE products IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS products (" +
            "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
            "name VARCHAR(255) NOT NULL," +
            "price BIGINT NOT NULL," +
            "imageurl VARCHAR(2083) NOT NULL" +
            ")");

        List<Object[]> initialData = Arrays.asList(
            new Object[]{"스타벅스 1만원 교환권", 10000, "http://example.com/image1.jpg"},
            new Object[]{"Jelly Bunny", 20000, "http://example.com/image2.jpg"},
            new Object[]{"Camper", 30000, "http://example.com/image3.jpg"}
        );

        jdbcTemplate.batchUpdate("INSERT INTO products (name, price, imageurl) VALUES (?, ?, ?)",
            initialData);

    }

}
