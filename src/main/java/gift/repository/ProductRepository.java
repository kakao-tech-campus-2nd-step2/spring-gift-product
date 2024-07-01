package gift.repository;

import gift.model.ProductModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    // JDBC 연결 정보
    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    // 상품 데이터를 저장하는 자료구조
    private final Map<Long, ProductModel> products = new HashMap<>();

    // 데이터베이스에서 상품 데이터를 불러오는 메서드
    public void loadProductsFromDatabase() {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT id, category, name, price FROM product";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String category = rs.getString("category");
                String name = rs.getString("name");
                long price = rs.getLong("price");

                // 상품 데이터를 ProductModel 객체로 생성하여 Map에 추가
                products.put(id, new ProductModel(id, category, name, (int) price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 특정 상품 데이터를 가져오는 메서드
    public ProductModel getProductById(long productId) {
        return products.get(productId);
    }

    // 기타 필요한 메서드들 추가 가능
}