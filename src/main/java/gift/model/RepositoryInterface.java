package gift.model;

import java.util.List;

public interface RepositoryInterface<T, ID> {
    void save(T entity);
    T find(ID id);
    void delete(T entity);
    List<T> findAll();
}
