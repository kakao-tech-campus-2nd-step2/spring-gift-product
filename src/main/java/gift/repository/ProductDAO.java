package gift.repository;

import gift.model.ProductRecord;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Repository
public class ProductDAO {
    private Map<Long, ProductRecord> records = new HashMap<Long, ProductRecord>();
    private long idTraker = 1;

    public ProductRecord[] getAllRecords() {
        return records.values().toArray(new ProductRecord[records.size()]);
    }

    public ProductRecord getRecord(long id) {
        if (!records.containsKey(id)) {
            throw new NoSuchElementException();
        }
        return records.get(id);
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
        if (records.containsKey(id)) {
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