package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception{
        dropProductTable();
        createProductTable();
    }


    private void dropProductTable(){
        var sql="DROP TABLE products IF EXISTS";
        jdbcTemplate.execute(sql);
    }

    private void createProductTable(){
        var sql= """
            create table products(
                id bigint,
                name varchar(255),
                price int,
                image_url varchar(255)
            )
            """;
        jdbcTemplate.execute(sql);
    }
}
