package gift;

import java.util.HashMap;
import java.util.Map;

public class ProductDAO {
    private Map<Long, ProductRecord> records = new HashMap<Long, ProductRecord>();
    private long idTraker = 1;

    private boolean isRecordExist(long id) {
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
}
