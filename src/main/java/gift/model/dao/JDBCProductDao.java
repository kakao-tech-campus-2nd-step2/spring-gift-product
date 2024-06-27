package gift.model.dao;

import gift.model.Product;
import gift.model.repository.ProductRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCProductDao implements ProductRepository {
    private static final String URL = "jdbc:h2:mem:test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private final String CREATE_PRODUCT_TABLE_QUERY = """
            CREATE TABLE IF NOT EXISTS products (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(255) NOT NULL,
                price int NOT NULL,
                image_url VARCHAR(255),
                is_deleted BOOLEAN DEFAULT FALSE
            );
        """;

    private final String INSERT_PRODUCT_QUERY = """
            INSERT INTO products (name, price, image_url, is_deleted) VALUES (?, ?, ?, ?);
        """;

    private final String SELECT_ALL_PRODUCTS_QUERY = """
            SELECT * FROM products;
        """;

    private final String SELECT_PRODUCT_BY_ID_QUERY = """
            SELECT * FROM products WHERE id = ?;
        """;

    private final String UPDATE_PRODUCT_QUERY = """
            UPDATE products SET name = ?, price = ?, image_url = ?, is_deleted = ? WHERE id = ?;
        """;

    private final String DELETE_PRODUCT_QUERY = """
            DELETE FROM products WHERE id = ?;
        """;


    public JDBCProductDao(){
        createTable();
    }

    @Override
    public void save(Product entity) {
        if(entity.isNew()){
            try{
                Connection connection = getConnection();
                PreparedStatement state = connection.prepareStatement(INSERT_PRODUCT_QUERY);
                state.setString(1, entity.getName());
                state.setInt(2, entity.getPrice());
                state.setString(3, entity.getImgUrl());
                state.setBoolean(4, entity.isDeleted());
                state.execute();
                state.close();
                connection.close();
            } catch (SQLException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
            return;
        }
        update(entity);
    }

    public void update(Product entity){
        try{
            Connection connection = getConnection();
            PreparedStatement state = connection.prepareStatement(UPDATE_PRODUCT_QUERY);
            state.setString(1, entity.getName());
            state.setInt(2, entity.getPrice());
            state.setString(3, entity.getImgUrl());
            state.setBoolean(4, entity.isDeleted());
            state.setLong(5, entity.getId());
            state.execute();
            state.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Product find(Long id) {
        Product product = null;
        try{
            Connection connection = getConnection();
            PreparedStatement state = connection.prepareStatement(SELECT_PRODUCT_BY_ID_QUERY);
            state.setLong(1, id);
            state.execute();
            if (state.getResultSet().next()){
                product = new Product(
                    state.getResultSet().getLong("id"),
                    state.getResultSet().getString("name"),
                    state.getResultSet().getInt("price"),
                    state.getResultSet().getString("image_url"),
                    state.getResultSet().getBoolean("is_deleted")
                );
            }
            state.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return product;
    }

    @Override
    public void delete(Product entity) {
        try{
            Connection connection = getConnection();
            PreparedStatement state = connection.prepareStatement(DELETE_PRODUCT_QUERY);
            state.setLong(1, entity.getId());
            state.execute(DELETE_PRODUCT_QUERY);
            state.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try{
            Connection connection = getConnection();
            Statement state = connection.createStatement();
            state.execute(SELECT_ALL_PRODUCTS_QUERY);
            while (state.getResultSet().next()){
                products.add(new Product(
                    state.getResultSet().getLong("id"),
                    state.getResultSet().getString("name"),
                    state.getResultSet().getInt("price"),
                    state.getResultSet().getString("image_url"),
                    state.getResultSet().getBoolean("is_deleted")
                ));
            }
            state.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return products;
    }

    private Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void createTable() {
        try{
            Connection connection = getConnection();
            Statement state = connection.createStatement();
            state.execute(CREATE_PRODUCT_TABLE_QUERY);
            connection.commit();
            state.close();
            connection.close();
            System.out.println("Table created");
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
