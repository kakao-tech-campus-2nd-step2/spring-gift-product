package gift.repository;

import gift.model.ProductRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Repository
public class ProductDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    Map<Long, ProductRecord> records = new HashMap<>();

    private long idTraker = 1;

    public ProductRecord[] getAllRecords() {
        String sql = "select * from products";

        return jdbcTemplate.query(sql, (record,rowNum) -> new ProductRecord(
                record.getLong("id"),
                record.getString("name"),
                record.getInt("price"),
                record.getString("imageUrl")
                )
            ).toArray(new ProductRecord[0]);
    }

    public ProductRecord getRecord(long id) {
        if (!isRecordExist(id)) {
            throw new NoSuchElementException();
        }

        String sql = "select * from products where id = ?";

        return jdbcTemplate.queryForObject(sql, ProductRecord.class, id);
    }

    public ProductRecord addNewRecord(ProductRecord product) {
        return addNewRecord(product, getNewId());
    }

    public ProductRecord addNewRecord(ProductRecord product, long id) throws DuplicateKeyException {
        if (isRecordExist(id)) {
            throw new DuplicateKeyException("A record with the given ID already exists.");
        }

        ProductRecord record = product.withId(id);
        records.put(id, record);

        return record;
    }

    public ProductRecord replaceRecord(long id, ProductRecord product) throws NoSuchElementException {
        if (!isRecordExist(id)) {
            throw new NoSuchElementException("Record not found");
        }

        ProductRecord record = product.withId(id);
        records.put(id, record);

        return record;
    }

    public ProductRecord updateRecord(long id, ProductRecord patch) throws NoSuchElementException {
        if (!isRecordExist(id)) {
            throw new NoSuchElementException("Record not found");
        }

        ProductRecord record = records.get(id).getUpdatedRecord(patch);
        records.put(id, record);

        return record;
    }

    public void deleteRecord(long id) throws NoSuchElementException {
        if (!isRecordExist(id)) {
            throw new NoSuchElementException("Record not found");
        }
        records.remove(id);
    }


    public boolean isRecordExist(long id) {
        String sql = "select count(*) from products where id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);

        if (count > 0) {
            return true;
        }

        return false;
    }

    private long getNewId() {
        while (isRecordExist(idTraker)) {
            idTraker++;
        }

        return idTraker;
    }
}
