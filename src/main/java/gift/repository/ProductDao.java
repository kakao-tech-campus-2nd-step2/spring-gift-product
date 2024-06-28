package gift.repository;

import gift.exception.ValidationException;
import gift.model.Product;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// db를 조작할 dao클래스
@Repository
public class ProductDao {
    // jdbc template
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create 처리 메서드
    public void insertProduct(Product product) {
        // 이미 존재하는 id에 넣으려고 하는지 검증
        long id = product.getId();
        verifyProductAlreadyExist(id);

        var sql = """
            insert into product (id, name, price, image) values (?, ?, ?, ?)
            """;
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImage());
    }

    // Read 처리 메서드
    public List<Product> selectProduct() {
        var sql = """
            select id, name, price, image
            from product;
            """;

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new Product(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("image")
        ));
    }

    // Update 처리 메서드
    public void updateProduct(long targetId, Product product) {
        // 이미 존재하는 id로 수정하면 안됨(자신 포함)
        long id = product.getId();
        verifyProductAlreadyExist(id);

        // 검증이 끝났으면 product의 검증도 거치고 변경
        Product targetProduct = selectProductWithId(targetId);
        targetProduct.updateAll(product);

        // 키를 변경하는 대신, old key를 제거하고 new key를 생성
        // 구현하면서 생각해보니 key는 못 바꾸게 하는 것이 더 편하고 자연스러웠을 것 같다.
        deleteProduct(targetId);
        insertProduct(targetProduct);
    }

    // Delete 처리 메서드
    public void deleteProduct(long id) {
        // 우선 해당하는 id가 있는지부터 검사
        verifyProductExist(id);

        var sql = """
            delete from product where id = ?;
            """;

        jdbcTemplate.update(sql, id);
    }

    // 모든 데이터 Delete 처리 메서드
    public void deleteAllProduct() {
        var sql = """
            delete from product;
            """;

        jdbcTemplate.execute(sql);
    }

    // db에서 특정 id를 갖는 로우를 반환
    private Product selectProductWithId(long id) {
        // 존재하는 id를 불러올 수 있도록 검증
        verifyProductExist(id);

        var sql = """
            select id, name, price, image
            from product
            where id = ?;
            """;

        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> new Product(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("image")
        ), id);
    }

    // db에서 특정 id를 갖는 로우가 있는지 확인하는 메서드
    private boolean isExistRow(long id) {
        var sql = """
            select id
            from product
            where id = ?;
            """;

        // 결과의 로우가 존재하는지 반환
        // 로우가 최대 한 개이므로 이보다는 더 좋은 방법이 있을 것 같지만 찾지 못했습니다.
        boolean isEmpty = jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getInt("id"), id).isEmpty();

        return !isEmpty;
    }

    // 도메인 규칙: 삽입 시에는 id가 중복되지 않아야 한다.
    private void verifyProductAlreadyExist(long id) {
        if (isExistRow(id)) {
            throw new ValidationException("id가 중복됩니다.");
        }
    }

    // 도메인 규칙: 조회 시에는 id가 존재해야 한다.
    private void verifyProductExist(long id) {
        if (!isExistRow(id)) {
            throw new NoSuchElementException("해당 id를 가진 제품이 존재하지 않습니다.");
        }
    }
}
