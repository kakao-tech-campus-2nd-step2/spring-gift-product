package gift;

import java.util.HashMap;
import java.util.Map;

public class ProductDAO {
    private Map<Long, ProductRecord> records = new HashMap<Long, ProductRecord>();
    private long id = 1;

    private boolean isRecordExist(long id) {
        if (records.containsKey(id)) {
            return true;
        }
        return false;
    }
}
