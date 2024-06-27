package gift;

import org.springframework.dao.DuplicateKeyException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ProductDAO {
    private Map<Long, ProductRecord> records = new HashMap<Long, ProductRecord>();
    private long idTraker = 1;

    public boolean isRecordExist(long id) {
        if (records.containsKey(id)) {
            return true;
        }

        return false;
    }

    private long getNewId() {
        if (isRecordExist(idTraker)) {
            idTraker++;
        }

        return idTraker;
    }

    public ProductRecord addNewRecord(ProductRecord product, long id) throws DuplicateKeyException {
        if (isRecordExist(id)) {
            throw new DuplicateKeyException("A record with the given ID already exists.");
        }

        ProductRecord record = new ProductRecord(id, product.name(), product.price(), product.imageUrl());
        records.put(id, record);

        return record;
    }

    public ProductRecord addNewRecord(ProductRecord product) {
        return addNewRecord(product, getNewId());
    }

    public ProductRecord[] getAllRecords() {
        return records.values().toArray(new ProductRecord[records.size()]);
    }

    public void deleteRecord(long id) throws NoSuchElementException {
        if (!isRecordExist(id)) {
            throw new NoSuchElementException("Record not found");
        }
        records.remove(id);
    }

    public ProductRecord replaceRecord(long id, ProductRecord product) throws NoSuchElementException {
        if (!isRecordExist(id)) {
            throw new NoSuchElementException("Record not found");
        }

        ProductRecord record = new ProductRecord(id, product.name(), product.price(), product.imageUrl());
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
}
